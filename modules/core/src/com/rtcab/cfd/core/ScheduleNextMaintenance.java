package com.rtcab.cfd.core;

import com.haulmont.addon.bproc.events.UserTaskCompletedEvent;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.DatatypeFormatter;
import com.haulmont.cuba.security.entity.User;
import com.rtcab.cfd.DateConversion;
import com.rtcab.cfd.entity.Equipment;
import de.diedavids.cuba.userinbox.entity.SendMessageEntity;
import de.diedavids.cuba.userinbox.service.MessageService;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Component(ScheduleNextMaintenance.NAME)
public class ScheduleNextMaintenance {
    public static final String NAME = "cfd_ScheduleNextMaintenance";

    @Inject
    private Logger log;
    @Inject
    private MessageService messageService;

    @Inject
    private BprocRuntimeService bprocRuntimeService;
    @Inject
    private DataManager dataManager;

    @Inject
    private DatatypeFormatter datatypeFormatter;

    @EventListener
    public void onUserTaskCompleted(UserTaskCompletedEvent event) {
        if (userTaskCompleted("equipment-maintenance", "schedule-next-maintenance", event)) {

            User supervisor = processSupervisor(event);
            User mechanic = event.getUser();
            Date scheduledAt = processScheduledAt(event);
            Equipment equipment = processEquipment(event);


            SendMessageEntity message = dataManager.create(SendMessageEntity.class);
            message.setSender(mechanic);
            message.setReceivers(Collections.singletonList(supervisor));
            message.setShareable(equipment);
            message.setSubject("Schedule next Maintenance");
            message.setText(String.format(
                    "The maintenance of %s performed by %s on %s requires a next Maintenance. <br /><br />" +
                            "Please arrange a new Maintenance. %s",
                    equipment.getName(),
                    mechanic.getName(),
                    datatypeFormatter.formatDate(scheduledAt),

                    Optional.ofNullable(processNextMaintenanceProposal(event))
                            .map(proposal -> "My Proposal would be: " + proposal)
                    .orElse("")
            ));

            messageService.sendMessage(message);
        }
    }

    private User processSupervisor(UserTaskCompletedEvent event) {
        return (User) getVariable(event, "supervisor");
    }

    private Equipment processEquipment(UserTaskCompletedEvent event) {
        return (Equipment) getVariable(event, "equipment");
    }
    private Date processScheduledAt(UserTaskCompletedEvent event) {
        return (Date) getVariable(event, "scheduledAt");
    }
    private String processNextMaintenanceProposal(UserTaskCompletedEvent event) {
        return (String) getVariable(event, "nextMaintenanceProposal");
    }

    private Object getVariable(UserTaskCompletedEvent event, String variableName) {
        return bprocRuntimeService.getVariable(event.getTaskData().getExecutionId(), variableName);
    }

    private boolean userTaskCompleted(String processKey, String taskKey, UserTaskCompletedEvent event) {
        return processKey.equals(event.getProcessDefinitionData().getKey()) &&
                taskKey.equals(event.getTaskData().getTaskDefinitionKey());
    }
}
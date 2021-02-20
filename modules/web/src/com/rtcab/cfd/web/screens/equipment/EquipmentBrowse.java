package com.rtcab.cfd.web.screens.equipment;

import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.NotificationFacet;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.rtcab.cfd.entity.Course;
import com.rtcab.cfd.entity.Employee;
import com.rtcab.cfd.entity.Equipment;
import com.rtcab.cfd.entity.Maintenance;

import javax.inject.Inject;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@UiController("cfd_Equipment.browse")
@UiDescriptor("equipment-browse.xml")
@LookupComponent("equipmentsTable")
@LoadDataBeforeShow
public class EquipmentBrowse extends StandardLookup<Equipment> {
    @Inject
    private GroupTable<Equipment> equipmentsTable;
    @Inject
    private BprocRuntimeService bprocRuntimeService;
    @Inject
    private NotificationFacet noEquipmentSelectedError;
    @Inject
    private NotificationFacet maintenanceScheduledNotification;
    @Inject
    private TimeSource timeSource;
    @Inject
    private DataManager dataManager;
    @Inject
    private UserSession userSession;

    @Subscribe("equipmentsTable.scheduleMaintenance")
    public void onEquipmentsTableScheduleMaintenance(Action.ActionPerformedEvent event) {
        Set<Equipment> equipmentsToSchedule = equipmentsTable.getSelected();

        if (equipmentsToSchedule.isEmpty()){
            noEquipmentSelectedError.show();
            return;
        }

        equipmentsToSchedule.forEach(this::scheduleMaintenanceFor);

        maintenanceScheduledNotification.show();
    }

    private void scheduleMaintenanceFor(Equipment equipment) {

        LocalDate scheduledAt = timeSource.now().toLocalDate().plusDays(new Random().nextInt(14) + 14);

        Maintenance maintenance = dataManager.create(Maintenance.class);
        maintenance.setEquipment(equipment);


        Map<String, Object> processVariables = new HashMap<>();
        processVariables.put("equipment", equipment);
        User currentUser = userSession.getCurrentOrSubstitutedUser();
        processVariables.put("supervisor", currentUser);
        processVariables.put("fireDepartment", equipment.getFireDepartment());
        processVariables.put("schedulingRangeStart", Date.valueOf(scheduledAt));
        processVariables.put("schedulingRangeEnd", Date.valueOf(scheduledAt.plusDays(7)));

        bprocRuntimeService.startProcessInstanceByKey(
                "equipment-maintenance",
                equipment.getName(),
                processVariables
        );

    }
}
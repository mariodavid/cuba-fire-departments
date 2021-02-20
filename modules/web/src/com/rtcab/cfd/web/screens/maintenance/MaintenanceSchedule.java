package com.rtcab.cfd.web.screens.maintenance;

import com.haulmont.addon.bproc.web.processform.Outcome;
import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.addon.bproc.web.processform.ProcessFormContext;
import com.haulmont.addon.bproc.web.processform.ProcessVariable;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.DatatypeFormatter;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.rtcab.cfd.entity.Equipment;
import com.rtcab.cfd.entity.FireDepartment;
import com.rtcab.cfd.entity.Maintenance;
import com.rtcab.cfd.entity.MaintenanceStatus;
import com.rtcab.cfd.service.EmployeeService;
import com.rtcab.cfd.web.screens.DateConversion;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@UiController("cfd_Maintenance.schedule")
@UiDescriptor("maintenance-schedule.xml")
@EditedEntityContainer("maintenanceDc")
@LoadDataBeforeShow
@ProcessForm(
        outcomes = {
                @Outcome(id = MaintenanceSchedule.SCHEDULED_OUTCOME),
                @Outcome(id = MaintenanceSchedule.NEED_TO_RESCHEDULE_OUTCOME)
        }
)
public class MaintenanceSchedule extends StandardEditor<Maintenance> {

    static final String SCHEDULED_OUTCOME = "Scheduled";
    static final String NEED_TO_RESCHEDULE_OUTCOME = "Need-to-Reschedule";

    @ProcessVariable(name = "equipment")
    protected Equipment equipment;

    @ProcessVariable(name = "fireDepartment")
    protected FireDepartment fireDepartment;

    @ProcessVariable(name = "schedulingRangeStart")
    protected Date schedulingRangeStart;

    @ProcessVariable(name = "schedulingRangeEnd")
    protected Date schedulingRangeEnd;

    @Inject
    protected ProcessFormContext processFormContext;
    @Inject
    private DataManager dataManager;
    @Inject
    private DateField<LocalDate> scheduledAtField;
    @Inject
    private Label<String> scheduledHelpText;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private DatatypeFormatter datatypeFormatter;
    @Inject
    private InstanceContainer<Equipment> equipmentDc;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private UserSession userSession;
    @Inject
    private TimeSource timeSource;
    @Inject
    private DateConversion dateConversion;

    @Subscribe
    protected void onInit(InitEvent event) {

        Maintenance maintenance = dataManager.create(Maintenance.class);
        maintenance.setStatus(MaintenanceStatus.CREATED);
        maintenance.setPerformedBy(employeeService.currentEmployee());

        Equipment equipmentComplete = dataManager.reload(this.equipment, "equipment-complete");
        maintenance.setEquipment(equipmentComplete);
        equipmentDc.setItem(equipmentComplete);

        setEntityToEdit(maintenance);

    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        LocalDate rangeStart = dateConversion.toLocalDate(schedulingRangeStart);
        LocalDate rangeEnd = dateConversion.toLocalDate(schedulingRangeEnd);
        scheduledAtField.setRangeStart(rangeStart);
        scheduledAtField.setRangeEnd(rangeEnd);


        scheduledHelpText.setValue(
                messageBundle.formatMessage(
                        "scheduledHelpText",
                        datatypeFormatter.formatLocalDate(rangeStart),
                        datatypeFormatter.formatLocalDate(rangeEnd)
                )
        );
    }


    @Subscribe("scheduledBtn")
    public void onScheduledBtnClick(Button.ClickEvent event) {


        Map<String, Object> processVariables = new HashMap<>();
        processVariables.put("maintenance", getEditedEntity());
        processVariables.put("mechanic", currentUser());

        /*
        for demonstration purposes the time is set to 'now()+1 minute' instead of the real scheduled At date
         */
        processVariables.put("scheduledAt", dateConversion.toDate(timeSource.now().plusMinutes(1).withSecond(0).toLocalDateTime()));
        //processVariables.put("scheduledAt", toDate(getEditedEntity().getScheduledAt().atTime(8, 0)));

        commitChanges()
                .then(() -> {
                    processFormContext.taskCompletion()
                            .withOutcome(SCHEDULED_OUTCOME)
                            .withProcessVariables(processVariables)
                            .complete();
                    closeWithDefaultAction();
                });
    }

    @Subscribe("needToRescheduleBtn")
    public void onNeedToRescheduleBtnClick(Button.ClickEvent event) {

        Map<String, Object> processVariables = new HashMap<>();
        processVariables.put("mechanic", currentUser());

        processFormContext.taskCompletion()
                .withOutcome(NEED_TO_RESCHEDULE_OUTCOME)
                .withProcessVariables(processVariables)
                .complete();

        closeWithDiscard();
    }

    private User currentUser() {
        return userSession.getCurrentOrSubstitutedUser();
    }
}
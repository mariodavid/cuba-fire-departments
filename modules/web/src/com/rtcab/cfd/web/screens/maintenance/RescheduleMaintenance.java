package com.rtcab.cfd.web.screens.maintenance;

import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.addon.bproc.web.processform.ProcessFormContext;
import com.haulmont.addon.bproc.web.processform.ProcessVariable;
import com.haulmont.cuba.core.global.DatatypeFormatter;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.DatePicker;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.rtcab.cfd.entity.Equipment;
import com.rtcab.cfd.DateConversion;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@UiController("cfd_RescheduleMaintenance")
@UiDescriptor("reschedule-maintenance.xml")
@ProcessForm
public class RescheduleMaintenance extends Screen {


    @ProcessVariable(name = "equipment")
    protected Equipment equipment;

    @ProcessVariable(name = "mechanic")
    protected User mechanic;

    @ProcessVariable(name = "schedulingRangeStart")
    protected Date schedulingRangeStart;

    @ProcessVariable(name = "schedulingRangeEnd")
    protected Date schedulingRangeEnd;


    @Inject
    private Label<String> needToRescheduleHelpText;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private DatatypeFormatter datatypeFormatter;

    @Inject
    protected ProcessFormContext processFormContext;
    @Inject
    private DatePicker<Date> rescheduledAt;
    @Inject
    private DateConversion dateConversion;
    @Inject
    private Notifications notifications;
    @Inject
    private UserSession userSession;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {

        LocalDate rangeStart = toLocalDate(schedulingRangeStart);
        LocalDate rangeEnd = toLocalDate(schedulingRangeEnd);

        needToRescheduleHelpText.setValue(
                messageBundle.formatMessage(
                        "needToRescheduleHelpText",
                        mechanic.getName(),
                        datatypeFormatter.formatLocalDate(rangeStart),
                        datatypeFormatter.formatLocalDate(rangeEnd)
                )
        );
    }

    public LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Subscribe("rescheduledBtn")
    public void onRescheduledBtnClick(Button.ClickEvent event) {
        Map<String, Object> processVariables = new HashMap<>();

        if (rescheduledAt.getValue() == null) {
            notifications
                    .create(Notifications.NotificationType.ERROR)
                    .withCaption(messageBundle.getMessage("rescheduleDateRequired"))
                    .show();
            return;
        }

        LocalDate beginningOfRescheduledWeek = dateConversion.startOfWeek(
                dateConversion.toLocalDate(rescheduledAt.getValue()),
                userSession.getLocale()
                );

        processVariables.put("schedulingRangeStart", dateConversion.toDate(beginningOfRescheduledWeek));
        processVariables.put("schedulingRangeEnd", dateConversion.toDate(beginningOfRescheduledWeek.plusDays(6)));

       processFormContext.taskCompletion()
                .withProcessVariables(processVariables)
                .complete();

        closeWithDefaultAction();
    }
}
package com.rtcab.cfd.web.screens.operation;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Operation;

import javax.inject.Inject;
import java.time.LocalDateTime;

@UiController("cfd_Operation.browse")
@UiDescriptor("operation-browse.xml")
@LookupComponent("operationsTable")
@LoadDataBeforeShow
public class OperationBrowse extends StandardLookup<Operation> {


    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Notifications notifications;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private Messages messages;
    @Inject
    private DataContext dataContext;


    @Subscribe("calendar")
    protected void onCalendarCalendarDayClick(Calendar.CalendarDayClickEvent<LocalDateTime> event) {
        Screen visitEditor = screenBuilders.editor(Operation.class, this)
                .newEntity()
                .withInitializer(visit -> {
                    visit.setStartsAt(event.getDate());
                    visit.setEndsAt(event.getDate().plusHours(1));
                })
                .withOpenMode(OpenMode.DIALOG)
                .build();

        visitEditor.addAfterCloseListener(afterCloseEvent -> {
            if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
                getScreenData().loadAll();
            }
        });

        visitEditor.show();
    }

    @Subscribe("calendar")
    protected void onCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<LocalDateTime> event) {

        Screen operationEditor = screenBuilders.editor(Operation.class, this)
                .editEntity((Operation) event.getEntity())
                .withOpenMode(OpenMode.DIALOG)
                .build();

        operationEditor.addAfterCloseListener(afterCloseEvent -> {
            if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
                getScreenData().loadAll();
            }
        });

        operationEditor.show();
    }


    @Subscribe("calendar")
    protected void onCalendarCalendarEventResize(Calendar.CalendarEventResizeEvent<LocalDateTime> event) {
        updateOperation(event.getEntity(), event.getNewStart(), event.getNewEnd());
    }

    @Subscribe("calendar")
    protected void onCalendarCalendarEventMove(Calendar.CalendarEventMoveEvent<LocalDateTime> event) {
        updateOperation(event.getEntity(), event.getNewStart(), event.getNewEnd());
    }

    private void updateOperation(Entity entity, LocalDateTime newStart, LocalDateTime newEnd) {
        Operation operation = (Operation) entity;
        operation.setStartsAt(newStart);
        operation.setEndsAt(newEnd);
        dataContext.commit();
        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption(
                        messageBundle.getMessage(
                                "operationUpdated"
                        )
                )
                .show();
    }


}
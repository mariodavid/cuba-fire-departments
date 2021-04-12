package com.rtcab.cfd.web.screens;

import com.google.common.collect.Lists;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.FileDescriptorResource;
import com.haulmont.cuba.gui.components.Image;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.Timer;
import com.haulmont.cuba.gui.components.mainwindow.SideMenu;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.app.main.MainScreen;
import com.rtcab.cfd.entity.Employee;
import com.rtcab.cfd.service.EmployeeService;
import de.diedavids.cuba.userinbox.web.screens.UserInboxMessageMenuBadge;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;


@UiController("fireMainScreen")
@UiDescriptor("fire-main-screen.xml")
public class FireMainScreen extends MainScreen {

    @Inject
    private SideMenu sideMenu;

    @Inject
    protected Timer updateCountersTimer;

    @Inject
    protected UserInboxMessageMenuBadge userInboxMessageMenuBadge;
    @Inject
    private Label<String> welcomeLabel;
    @Inject
    private UserSession userSession;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private Image avatarImage;
    @Inject
    private UserSessionSource userSessionSource;

    @Subscribe
    protected void onInit(InitEvent event) {
        userInboxMessageMenuBadge.initMessagesMenuItem(
                sideMenu,
                updateCountersTimer,
                this
        );

        updateCountersTimer.setDelay(3000);
        updateCountersTimer.start();
    }

    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {
        userInboxMessageMenuBadge.updateMessageCounter(sideMenu);

        Employee currentEmployee = employeeService.currentEmployee();

        if (currentEmployee != null) {
            welcomeLabel.setValue(randomOfList(Lists.newArrayList("Hi", "Hello", "Howdy", "\uD83E\uDD1C")) + ", " +
                    currentEmployee.getFirstName() + " " + currentEmployee.getName()
            );

            avatarImage.setSource(FileDescriptorResource.class)
                    .setFileDescriptor(currentEmployee.getAvatar());
        }
        else {
            welcomeLabel.setValue("Hello, " +
                    userSessionSource.getUserSession().getUser().getName()
            );
        }



    }

    private <T> T randomOfList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(new Random().nextInt(list.size()));
    }

    @Subscribe("updateCountersTimer")
    public void onUpdateCountersTimerTimerAction(Timer.TimerActionEvent event) {
        userInboxMessageMenuBadge.updateMessageCounter(sideMenu);
    }
}
package com.rtcab.cfd.web.screens;

import com.haulmont.cuba.gui.components.Timer;
import com.haulmont.cuba.gui.components.mainwindow.SideMenu;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.web.app.main.MainScreen;
import de.diedavids.cuba.userinbox.web.screens.UserInboxMessageMenuBadge;

import javax.inject.Inject;


@UiController("fireMainScreen")
@UiDescriptor("fire-main-screen.xml")
public class FireMainScreen extends MainScreen {

    @Inject
    private SideMenu sideMenu;

    @Inject
    protected Timer updateCountersTimer;

    @Inject
    protected UserInboxMessageMenuBadge userInboxMessageMenuBadge;

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
    }



    @Subscribe("updateCountersTimer")
    public void onUpdateCountersTimerTimerAction(Timer.TimerActionEvent event) {
        userInboxMessageMenuBadge.updateMessageCounter(sideMenu);
    }
}
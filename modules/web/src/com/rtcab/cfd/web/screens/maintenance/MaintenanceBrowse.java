package com.rtcab.cfd.web.screens.maintenance;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Maintenance;

@UiController("cfd_Maintenance.browse")
@UiDescriptor("maintenance-browse.xml")
@LookupComponent("maintenancesTable")
@LoadDataBeforeShow
public class MaintenanceBrowse extends StandardLookup<Maintenance> {
}
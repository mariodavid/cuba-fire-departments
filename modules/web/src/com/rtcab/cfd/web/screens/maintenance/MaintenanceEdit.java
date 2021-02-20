package com.rtcab.cfd.web.screens.maintenance;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Maintenance;

@UiController("cfd_Maintenance.edit")
@UiDescriptor("maintenance-edit.xml")
@EditedEntityContainer("maintenanceDc")
@LoadDataBeforeShow
public class MaintenanceEdit extends StandardEditor<Maintenance> {
}
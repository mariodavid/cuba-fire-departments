package com.rtcab.cfd.web.screens.firedepartment;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.FireDepartment;

@UiController("cfd_FireDepartment.browse")
@UiDescriptor("fire-department-browse.xml")
@LookupComponent("fireDepartmentsTable")
@LoadDataBeforeShow
public class FireDepartmentBrowse extends StandardLookup<FireDepartment> {
}
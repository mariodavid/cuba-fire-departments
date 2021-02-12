package com.rtcab.cfd.web.screens.firedepartment;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.FireDepartment;

@UiController("cfd_FireDepartment.edit")
@UiDescriptor("fire-department-edit.xml")
@EditedEntityContainer("fireDepartmentDc")
@LoadDataBeforeShow
public class FireDepartmentEdit extends StandardEditor<FireDepartment> {
}
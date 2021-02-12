package com.rtcab.cfd.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Employee;

@UiController("cfd_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
@LoadDataBeforeShow
public class EmployeeEdit extends StandardEditor<Employee> {
}
package com.rtcab.cfd.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Employee;

@UiController("cfd_Employee.browse")
@UiDescriptor("employee-browse.xml")
@LookupComponent("employeesTable")
@LoadDataBeforeShow
public class EmployeeBrowse extends StandardLookup<Employee> {
}
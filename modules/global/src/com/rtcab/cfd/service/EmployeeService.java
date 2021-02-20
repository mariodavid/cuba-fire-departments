package com.rtcab.cfd.service;

import com.rtcab.cfd.entity.Employee;

public interface EmployeeService {
    String NAME = "cfd_EmployeeService";

    Employee currentEmployee();
}
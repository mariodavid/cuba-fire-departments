package com.rtcab.cfd.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import com.rtcab.cfd.entity.Employee;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service(EmployeeService.NAME)
public class EmployeeServiceBean implements EmployeeService {

    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private DataManager dataManager;

    @Override
    public Employee currentEmployee() {
        User currentUser = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
        Optional<Employee> employee = dataManager.load(Employee.class)
                .query("e.user = ?1", currentUser)
                .view("employee-complete")
                .optional();

        return employee.orElse(null);
    }
}
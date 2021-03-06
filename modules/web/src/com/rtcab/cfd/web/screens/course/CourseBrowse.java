package com.rtcab.cfd.web.screens.course;

import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.NotificationFacet;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.rtcab.cfd.entity.Course;
import com.rtcab.cfd.entity.Employee;
import com.rtcab.cfd.service.EmployeeService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@UiController("cfd_Course.browse")
@UiDescriptor("course-browse.xml")
@LookupComponent("coursesTable")
@LoadDataBeforeShow
public class CourseBrowse extends StandardLookup<Course> {


    @Inject
    private BprocRuntimeService bprocRuntimeService;
    @Inject
    private GroupTable<Course> coursesTable;
    @Inject
    private UserSession userSession;
    @Inject
    private NotificationFacet enrollmentOnlyPossibleForEmployeesError;
    @Inject
    private NotificationFacet enrollmentRequestSendNotification;
    @Inject
    private EmployeeService employeeService;

    @Subscribe("coursesTable.enroll")
    public void onCoursesTableEnroll(Action.ActionPerformedEvent event) {

        Course course = coursesTable.getSingleSelected();

        User currentUser = userSession.getCurrentOrSubstitutedUser();
        Optional<Employee> currentEmployee = Optional.ofNullable(employeeService.currentEmployee());


        if (!currentEmployee.isPresent()){
            enrollmentOnlyPossibleForEmployeesError.show();
            return;
        }


        Map<String, Object> processVariables = new HashMap<>();
        processVariables.put("course", course);
        processVariables.put("employee", currentEmployee.get());
        processVariables.put("requestor", currentUser);
        bprocRuntimeService.startProcessInstanceByKey(
                "course-enrollment",
                course.getTopic().getTitle(),
                processVariables
        );

        enrollmentRequestSendNotification.show();

    }
}
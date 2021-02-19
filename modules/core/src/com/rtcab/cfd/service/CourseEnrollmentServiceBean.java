package com.rtcab.cfd.service;

import com.haulmont.cuba.core.global.DataManager;
import com.rtcab.cfd.entity.Course;
import com.rtcab.cfd.entity.Employee;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Service(CourseEnrollmentService.NAME)
public class CourseEnrollmentServiceBean implements CourseEnrollmentService {

    @Inject
    private DataManager dataManager;

    @Override
    public void enrollEmployee(Course course, Employee employee) {
        Course courseWithAllInformation = dataManager.reload(course, "course-complete");

        Set<Employee> participants = participantsOf(courseWithAllInformation);
        participants.add(employee);

        courseWithAllInformation.setParticipants(participants);

        dataManager.commit(courseWithAllInformation);
    }

    private Set<Employee> participantsOf(Course courseWithAllInformation) {
        Set<Employee> participants = courseWithAllInformation.getParticipants();

        if (participants == null) {
            return new HashSet<>();
        }
        return participants;
    }
}
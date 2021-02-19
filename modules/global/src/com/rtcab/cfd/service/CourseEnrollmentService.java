package com.rtcab.cfd.service;

import com.rtcab.cfd.entity.Course;
import com.rtcab.cfd.entity.Employee;

public interface CourseEnrollmentService {
    String NAME = "cfd_CourseEnrollmentService";

    /**
     * enrolls a employee to a given course.
     * In case the employee is already enrolled, nothing happens
     * @param course the course to enroll to
     * @param employee the employee to enroll
     */
    void enrollEmployee(Course course, Employee employee);
}
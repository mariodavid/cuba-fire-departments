package com.rtcab.cfd.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Table(name = "CFD_EMPLOYEE")
@Entity(name = "cfd_Employee")
@NamePattern("%s|name")
public class Employee extends StandardEntity {
    private static final long serialVersionUID = -1683508467855912395L;

    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "BIRTHDATE")
    private LocalDate birthdate;
    @JoinTable(name = "CFD_COURSE_EMPLOYEE_LINK",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    @ManyToMany
    private List<Course> courses;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FIRE_DEPARTMENT_ID")
    private FireDepartment fireDepartment;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @Lookup(type = LookupType.DROPDOWN, actions = {})
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FireDepartment getFireDepartment() {
        return fireDepartment;
    }

    public void setFireDepartment(FireDepartment fireDepartment) {
        this.fireDepartment = fireDepartment;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
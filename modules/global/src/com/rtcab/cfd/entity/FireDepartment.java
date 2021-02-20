package com.rtcab.cfd.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Table(name = "CFD_FIRE_DEPARTMENT")
@Entity(name = "cfd_FireDepartment")
@NamePattern("%s|name")
public class FireDepartment extends StandardEntity {
    private static final long serialVersionUID = -6006701624011518410L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "responsibleDepartment")
    private List<Operation> operations;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "fireDepartment")
    private List<Employee> employees;

    @JoinColumn(name = "FIRE_CHIEF_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @Lookup(type = LookupType.DROPDOWN, actions = {})
    private Employee fireChief;

    @Column(name = "STREET")
    private String street;

    @Column(name = "HOUSENUMBER")
    private String housenumber;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @NotNull
    @Column(name = "TYPE_", nullable = false)
    private String type;

    public Employee getFireChief() {
        return fireChief;
    }

    public void setFireChief(Employee fireChief) {
        this.fireChief = fireChief;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public FireDepartmentType getType() {
        return type == null ? null : FireDepartmentType.fromId(type);
    }

    public void setType(FireDepartmentType type) {
        this.type = type == null ? null : type.getId();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> mechanics() {
        if (CollectionUtils.isEmpty(getEmployees()))  {
            return Collections.emptyList();
        }

        return getEmployees().stream()
                .filter(Employee::getMechanic)
                .map(Employee::getUser)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
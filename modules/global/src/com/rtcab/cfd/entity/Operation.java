package com.rtcab.cfd.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "CFD_OPERATION")
@Entity(name = "cfd_Operation")
@NamePattern("%s (%s - %s)|title,startsAt,endsAt")
public class Operation extends StandardEntity {
    private static final long serialVersionUID = -8943891362501235336L;

    @Column(name = "TITLE")
    private String title;

    @Lookup(type = LookupType.DROPDOWN, actions = {})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RESPONSIBLE_DEPARTMENT_ID")
    private FireDepartment responsibleDepartment;

    @Lookup(type = LookupType.DROPDOWN, actions = {})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPERATION_LEAD_ID")
    private Employee operationLead;

    @NotNull
    @Column(name = "STARTS_AT", nullable = false)
    private LocalDateTime startsAt;

    @Column(name = "ENDS_AT")
    private LocalDateTime endsAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    private OperationType type;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "operation")
    private List<OperationReport> reports;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<OperationReport> getReports() {
        return reports;
    }

    public void setReports(List<OperationReport> reports) {
        this.reports = reports;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalDateTime endsAt) {
        this.endsAt = endsAt;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public Employee getOperationLead() {
        return operationLead;
    }

    public void setOperationLead(Employee operationLead) {
        this.operationLead = operationLead;
    }

    public FireDepartment getResponsibleDepartment() {
        return responsibleDepartment;
    }

    public void setResponsibleDepartment(FireDepartment responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment;
    }
}
package com.rtcab.cfd.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "CFD_MAINTENANCE")
@Entity(name = "cfd_Maintenance")
@NamePattern("%s - %s|equipment,performedAt")
public class Maintenance extends StandardEntity {
    private static final long serialVersionUID = 1571584068255099686L;

    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EQUIPMENT_ID")
    private Equipment equipment;

    @NotNull
    @Column(name = "SCHEDULED_AT", nullable = false)
    private LocalDate scheduledAt;

    @Column(name = "PERFORMED_AT")
    private LocalDateTime performedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFORMED_BY_ID")
    private Employee performedBy;

    @NotNull
    @Column(name = "STATUS", nullable = false)
    private String status;

    public MaintenanceStatus getStatus() {
        return status == null ? null : MaintenanceStatus.fromId(status);
    }

    public void setStatus(MaintenanceStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public Employee getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(Employee performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getPerformedAt() {
        return performedAt;
    }

    public void setPerformedAt(LocalDateTime performedAt) {
        this.performedAt = performedAt;
    }

    public LocalDate getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDate scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
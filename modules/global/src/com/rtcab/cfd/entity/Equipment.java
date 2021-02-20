package com.rtcab.cfd.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "CFD_EQUIPMENT")
@Entity(name = "cfd_Equipment")
@NamePattern("%s|name")
public class Equipment extends StandardEntity {
    private static final long serialVersionUID = 8553146416657772270L;

    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @NotNull
    @Column(name = "TYPE_", nullable = false)
    private String type;

    @OneToMany(mappedBy = "equipment")
    private List<Maintenance> maintenances;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open", "clear"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FIRE_DEPARTMENT_ID")
    private FireDepartment fireDepartment;

    @Column(name = "MAINTENANCE_INTERVAL")
    private String maintenanceInterval;

    public MaintenanceInterval getMaintenanceInterval() {
        return maintenanceInterval == null ? null : MaintenanceInterval.fromId(maintenanceInterval);
    }

    public void setMaintenanceInterval(MaintenanceInterval maintenanceInterval) {
        this.maintenanceInterval = maintenanceInterval == null ? null : maintenanceInterval.getId();
    }

    public FireDepartment getFireDepartment() {
        return fireDepartment;
    }

    public void setFireDepartment(FireDepartment fireDepartment) {
        this.fireDepartment = fireDepartment;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public EquipmentType getType() {
        return type == null ? null : EquipmentType.fromId(type);
    }

    public void setType(EquipmentType type) {
        this.type = type == null ? null : type.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
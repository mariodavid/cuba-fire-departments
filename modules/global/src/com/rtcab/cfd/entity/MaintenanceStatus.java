package com.rtcab.cfd.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum MaintenanceStatus implements EnumClass<String> {

    CREATED("CREATED", false),
    IN_PROGRESS("IN_PROGRESSB", false),
    CANCELLED("CANCELLED", true),
    DONE("DONE", true);

    private String id;
    private final boolean performed;

    MaintenanceStatus(String value, boolean performed) {
        this.id = value;
        this.performed = performed;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static MaintenanceStatus fromId(String id) {
        for (MaintenanceStatus at : MaintenanceStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }

    public boolean performed() {
        return performed;
    }
}
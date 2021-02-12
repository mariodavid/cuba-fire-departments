package com.rtcab.cfd.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum MaintenanceStatus implements EnumClass<String> {

    CREATED("CREATED"),
    IN_PROGRESS("IN_PROGRESSB"),
    CANCELLED("CANCELLED"),
    DONE("DONE");

    private String id;

    MaintenanceStatus(String value) {
        this.id = value;
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
}
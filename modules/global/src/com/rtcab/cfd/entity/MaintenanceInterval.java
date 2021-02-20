package com.rtcab.cfd.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum MaintenanceInterval implements EnumClass<String> {

    YEARLY("YEARLY"),
    QUARTERLY("QUARTERLY"),
    MONTHLY("MONTHLY");

    private String id;

    MaintenanceInterval(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static MaintenanceInterval fromId(String id) {
        for (MaintenanceInterval at : MaintenanceInterval.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
package com.rtcab.cfd.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum FireDepartmentType implements EnumClass<String> {

    VOLUNTEER("VOLUNTEER"),
    PROFESSIONAL("PROFESSIONAL");

    private String id;

    FireDepartmentType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static FireDepartmentType fromId(String id) {
        for (FireDepartmentType at : FireDepartmentType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
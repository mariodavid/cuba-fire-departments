package com.rtcab.cfd.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum EquipmentType implements EnumClass<String> {

    VEHICLE("VEHICLE"),
    SUPPLIES("SUPPLIES"),
    TUBE("TUBE");

    private String id;

    EquipmentType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static EquipmentType fromId(String id) {
        for (EquipmentType at : EquipmentType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
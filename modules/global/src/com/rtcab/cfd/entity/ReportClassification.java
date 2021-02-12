package com.rtcab.cfd.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ReportClassification implements EnumClass<String> {

    UNCLASSIFIED("UNCLASSIFIED"),
    CONFIDENTIAL("CONFIDENTIAL"),
    TOP_SECRET("TOP_SECRET");

    private String id;

    ReportClassification(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ReportClassification fromId(String id) {
        for (ReportClassification at : ReportClassification.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
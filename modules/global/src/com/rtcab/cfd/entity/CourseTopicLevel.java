package com.rtcab.cfd.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum CourseTopicLevel implements EnumClass<String> {

    BEGINNER("BEGINNER"),
    INTERMEDIATE("INTERMEDIATE"),
    EXPERT("EXPERT");

    private String id;

    CourseTopicLevel(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static CourseTopicLevel fromId(String id) {
        for (CourseTopicLevel at : CourseTopicLevel.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
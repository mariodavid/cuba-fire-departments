package com.rtcab.cfd.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "CFD_COURSE_TOPIC_ATTACHMENT")
@Entity(name = "cfd_CourseTopicAttachment")
@NamePattern("%s|name")
public class CourseTopicAttachment extends StandardEntity {
    private static final long serialVersionUID = -3334925641510338189L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FILE_ID")
    private FileDescriptor file;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COURSE_TOPIC_ID")
    private CourseTopic courseTopic;

    public CourseTopic getCourseTopic() {
        return courseTopic;
    }

    public void setCourseTopic(CourseTopic courseTopic) {
        this.courseTopic = courseTopic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileDescriptor getFile() {
        return file;
    }

    public void setFile(FileDescriptor file) {
        this.file = file;
    }
}
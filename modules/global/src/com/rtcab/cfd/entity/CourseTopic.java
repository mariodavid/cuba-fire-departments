package com.rtcab.cfd.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "CFD_COURSE_TOPIC")
@Entity(name = "cfd_CourseTopic")
@NamePattern("%s|title")
public class CourseTopic extends StandardEntity {
    private static final long serialVersionUID = -6654188154617788252L;

    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Lob
    @Column(name = "SUMMARY")
    private String summary;

    @NotNull
    @Column(name = "LEVEL_", nullable = false)
    private String level;

    @Column(name = "CONTENT")
    @Lob
    private String content;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "courseTopic")
    private List<CourseTopicAttachment> attachments;

    public List<CourseTopicAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<CourseTopicAttachment> attachments) {
        this.attachments = attachments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CourseTopicLevel getLevel() {
        return level == null ? null : CourseTopicLevel.fromId(level);
    }

    public void setLevel(CourseTopicLevel level) {
        this.level = level == null ? null : level.getId();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
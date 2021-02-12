package com.rtcab.cfd.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "CFD_OPERATION_REPORT")
@Entity(name = "cfd_OperationReport")
@NamePattern("%s|title")
public class OperationReport extends StandardEntity {
    private static final long serialVersionUID = 6841326866272066633L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OPERATION_ID")
    private Operation operation;

    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "SUMMARY")
    private String summary;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CREATOR_ID")
    private Employee creator;

    @NotNull
    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @NotNull
    @Column(name = "CLASSIFICATION", nullable = false)
    private String classification;

    public ReportClassification getClassification() {
        return classification == null ? null : ReportClassification.fromId(classification);
    }

    public void setClassification(ReportClassification classification) {
        this.classification = classification == null ? null : classification.getId();
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Employee getCreator() {
        return creator;
    }

    public void setCreator(Employee creator) {
        this.creator = creator;
    }
}
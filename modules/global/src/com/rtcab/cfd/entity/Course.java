package com.rtcab.cfd.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "CFD_COURSE")
@Entity(name = "cfd_Course")
@NamePattern("%s: %s - %s|topic,startsAt,endsAt")
public class Course extends StandardEntity {
    private static final long serialVersionUID = 3559732508002428446L;

    @JoinTable(name = "CFD_COURSE_EMPLOYEE_LINK",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    @ManyToMany
    private Set<Employee> participants;

    @NotNull
    @Column(name = "STARTS_AT", nullable = false)
    private LocalDateTime startsAt;

    @NotNull
    @Column(name = "ENDS_AT", nullable = false)
    private LocalDateTime endsAt;

    @Lookup(type = LookupType.SCREEN, actions = "lookup")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COURSE_LEADER_ID")
    private Employee courseLeader;

    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TOPIC_ID")
    private CourseTopic topic;

    public void setParticipants(Set<Employee> participants) {
        this.participants = participants;
    }

    public Set<Employee> getParticipants() {
        return participants;
    }

    public CourseTopic getTopic() {
        return topic;
    }

    public void setTopic(CourseTopic topic) {
        this.topic = topic;
    }

    public Employee getCourseLeader() {
        return courseLeader;
    }

    public void setCourseLeader(Employee courseLeader) {
        this.courseLeader = courseLeader;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalDateTime endsAt) {
        this.endsAt = endsAt;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

}
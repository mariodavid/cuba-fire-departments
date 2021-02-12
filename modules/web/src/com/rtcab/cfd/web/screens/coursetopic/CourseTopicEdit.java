package com.rtcab.cfd.web.screens.coursetopic;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.CourseTopic;

@UiController("cfd_CourseTopic.edit")
@UiDescriptor("course-topic-edit.xml")
@EditedEntityContainer("courseTopicDc")
@LoadDataBeforeShow
public class CourseTopicEdit extends StandardEditor<CourseTopic> {
}
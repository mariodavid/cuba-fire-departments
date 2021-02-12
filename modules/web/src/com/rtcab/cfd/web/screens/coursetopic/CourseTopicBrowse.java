package com.rtcab.cfd.web.screens.coursetopic;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.CourseTopic;

@UiController("cfd_CourseTopic.browse")
@UiDescriptor("course-topic-browse.xml")
@LookupComponent("courseTopicsTable")
@LoadDataBeforeShow
public class CourseTopicBrowse extends StandardLookup<CourseTopic> {
}
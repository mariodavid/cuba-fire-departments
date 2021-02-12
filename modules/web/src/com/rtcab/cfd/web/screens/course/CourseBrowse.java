package com.rtcab.cfd.web.screens.course;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Course;

@UiController("cfd_Course.browse")
@UiDescriptor("course-browse.xml")
@LookupComponent("coursesTable")
@LoadDataBeforeShow
public class CourseBrowse extends StandardLookup<Course> {
}
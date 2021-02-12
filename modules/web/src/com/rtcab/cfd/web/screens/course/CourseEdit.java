package com.rtcab.cfd.web.screens.course;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Course;

@UiController("cfd_Course.edit")
@UiDescriptor("course-edit.xml")
@EditedEntityContainer("courseDc")
@LoadDataBeforeShow
public class CourseEdit extends StandardEditor<Course> {
}
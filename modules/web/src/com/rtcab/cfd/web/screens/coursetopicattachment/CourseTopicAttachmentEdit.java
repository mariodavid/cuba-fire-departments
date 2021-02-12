package com.rtcab.cfd.web.screens.coursetopicattachment;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.CourseTopicAttachment;

@UiController("cfd_CourseTopicAttachment.edit")
@UiDescriptor("course-topic-attachment-edit.xml")
@EditedEntityContainer("courseTopicAttachmentDc")
@LoadDataBeforeShow
public class CourseTopicAttachmentEdit extends StandardEditor<CourseTopicAttachment> {
}
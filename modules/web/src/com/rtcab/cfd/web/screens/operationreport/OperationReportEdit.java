package com.rtcab.cfd.web.screens.operationreport;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.OperationReport;

@UiController("cfd_OperationReport.edit")
@UiDescriptor("operation-report-edit.xml")
@EditedEntityContainer("operationReportDc")
@LoadDataBeforeShow
public class OperationReportEdit extends StandardEditor<OperationReport> {
}
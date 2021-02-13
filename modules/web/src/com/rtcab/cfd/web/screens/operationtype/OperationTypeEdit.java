package com.rtcab.cfd.web.screens.operationtype;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.OperationType;

@UiController("cfd_OperationType.edit")
@UiDescriptor("operation-type-edit.xml")
@EditedEntityContainer("operationTypeDc")
@LoadDataBeforeShow
public class OperationTypeEdit extends StandardEditor<OperationType> {
}
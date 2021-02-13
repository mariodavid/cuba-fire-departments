package com.rtcab.cfd.web.screens.operation;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Operation;

@UiController("cfd_Operation.edit")
@UiDescriptor("operation-edit.xml")
@EditedEntityContainer("operationDc")
@LoadDataBeforeShow
public class OperationEdit extends StandardEditor<Operation> {
}
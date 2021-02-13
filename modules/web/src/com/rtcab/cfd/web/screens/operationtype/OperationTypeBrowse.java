package com.rtcab.cfd.web.screens.operationtype;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.OperationType;

@UiController("cfd_OperationType.browse")
@UiDescriptor("operation-type-browse.xml")
@LookupComponent("operationTypesTable")
@LoadDataBeforeShow
public class OperationTypeBrowse extends StandardLookup<OperationType> {
}
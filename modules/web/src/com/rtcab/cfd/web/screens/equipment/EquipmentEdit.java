package com.rtcab.cfd.web.screens.equipment;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Equipment;

@UiController("cfd_Equipment.edit")
@UiDescriptor("equipment-edit.xml")
@EditedEntityContainer("equipmentDc")
@LoadDataBeforeShow
public class EquipmentEdit extends StandardEditor<Equipment> {
}
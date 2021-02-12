package com.rtcab.cfd.web.screens.equipment;

import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Equipment;

@UiController("cfd_Equipment.browse")
@UiDescriptor("equipment-browse.xml")
@LookupComponent("equipmentsTable")
@LoadDataBeforeShow
public class EquipmentBrowse extends StandardLookup<Equipment> {
}
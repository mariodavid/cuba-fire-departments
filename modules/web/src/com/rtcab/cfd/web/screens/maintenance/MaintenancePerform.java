package com.rtcab.cfd.web.screens.maintenance;

import com.haulmont.addon.bproc.web.processform.Outcome;
import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.addon.bproc.web.processform.ProcessFormContext;
import com.haulmont.addon.bproc.web.processform.ProcessVariable;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.rtcab.cfd.entity.Equipment;
import com.rtcab.cfd.entity.FireDepartment;
import com.rtcab.cfd.entity.Maintenance;

import javax.inject.Inject;
import java.util.*;

@UiController("cfd_Maintenance.perform")
@UiDescriptor("maintenance-perform.xml")
@EditedEntityContainer("maintenanceDc")
@LoadDataBeforeShow
@ProcessForm(
        outcomes = {
                @Outcome(id = MaintenancePerform.COMPLETED_OUTCOME),
                @Outcome(id = MaintenancePerform.CANCELLED_OUTCOME)
        }
)
public class MaintenancePerform extends StandardEditor<Maintenance> {

    static final String COMPLETED_OUTCOME = "complete";
    static final String CANCELLED_OUTCOME = "cancel";

    @ProcessVariable(name = "equipment")
    protected Equipment equipment;

    @ProcessVariable(name = "maintenance")
    protected Maintenance maintenance;

    @ProcessVariable(name = "fireDepartment")
    protected FireDepartment fireDepartment;

    @Inject
    protected ProcessFormContext processFormContext;
    @Inject
    private DataManager dataManager;

    @Inject
    private InstanceContainer<Equipment> equipmentDc;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private BrowserFrame operationalModelPdf;
    @Inject
    private CollectionPropertyContainer<Maintenance> maintenancesForEquipmentDc;
    @Inject
    private Label<String> remarkFromLastMaintenanceField;
    @Inject
    private GroupBoxLayout remarkFromLastMaintenanceGroupBox;
    @Inject
    private GroupBoxLayout generalMaintenanceRemarksGroupBox;
    @Inject
    private Label<String> generalMaintenanceRemarksField;
    @Inject
    private TabSheet tabSheet;

    @Subscribe
    protected void onInit(InitEvent event) {
        setEntityToEdit(maintenance);


        Equipment equipmentComplete = dataManager.reload(this.equipment, "equipment-complete");
        maintenance.setEquipment(equipmentComplete);
        equipmentDc.setItem(equipmentComplete);

        getWindow().setCaption(messageBundle.formatMessage("maintenancePerform.caption", equipmentComplete.getName()));

        if (equipmentComplete.getOperationalManual() != null) {

            operationalModelPdf.setSource(FileDescriptorResource.class)
                    .setFileDescriptor(equipmentComplete.getOperationalManual())
                    .setMimeType("application/pdf");

            tabSheet.getTab("operationalManual")
                    .setVisible(true);
        } else {
            tabSheet.getTab("operationalManual")
                    .setVisible(false);
        }

    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {

        Optional<Maintenance> lastPerformedMaintenance = maintenancesForEquipmentDc.getItems()
                .stream()
                .filter(Maintenance::performed)
                .max(Comparator.comparing(Maintenance::getPerformedAt));

        lastPerformedMaintenance.ifPresent(it ->
            renderRemark(
                remarkFromLastMaintenanceGroupBox,
                remarkFromLastMaintenanceField,
                Optional.ofNullable(it.getNextMaintenanceRemark())
            )
        );

        renderRemark(
                generalMaintenanceRemarksGroupBox,
                generalMaintenanceRemarksField,
                Optional.ofNullable(equipmentDc.getItem().getGeneralMaintenanceRemarks())
        );
    }

    private void renderRemark(GroupBoxLayout remarkGroup, Label<String> remarkField, Optional<String> potentialRemark) {
        if (potentialRemark.isPresent()) {
            remarkField.setValue(potentialRemark.get());
            remarkGroup.setVisible(true);
            remarkGroup.setExpanded(true);
        }
    }

    @Subscribe("cancelBtn")
    public void onCancelBtnClick(Button.ClickEvent event) {
        performMaintenance(CANCELLED_OUTCOME);
    }

    @Subscribe("completeBtn")
    public void onCompleteBtnClick(Button.ClickEvent event) {
        performMaintenance(COMPLETED_OUTCOME);
    }

    private void performMaintenance(String completedOutcome) {
        commitChanges()
                .then(() -> {
                    processFormContext.taskCompletion()
                            .withOutcome(completedOutcome)
                            .complete();
                    closeWithDefaultAction();
                });
    }


}
package com.rtcab.cfd.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.TimeSource;
import com.rtcab.cfd.entity.Maintenance;
import com.rtcab.cfd.entity.MaintenanceStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(MaintenanceService.NAME)
public class MaintenanceServiceBean implements MaintenanceService {

    @Inject
    private DataManager dataManager;
    @Inject
    private TimeSource timeSource;

    @Override
    public void finishMaintenance(Maintenance maintenance) {
        updateMaintenance(maintenance, MaintenanceStatus.DONE);
    }

    @Override
    public void cancelMaintenance(Maintenance maintenance) {
        updateMaintenance(maintenance, MaintenanceStatus.CANCELLED);
    }

    private void updateMaintenance(Maintenance maintenance, MaintenanceStatus done) {
        maintenance.setStatus(done);
        maintenance.setPerformedAt(timeSource.now().toLocalDateTime());
        dataManager.commit(maintenance);
    }

}
package com.rtcab.cfd.service;

import com.rtcab.cfd.entity.Maintenance;

public interface MaintenanceService {
    String NAME = "cfd_MaintenanceService";

    void finishMaintenance(Maintenance maintenance);
    void cancelMaintenance(Maintenance maintenance);

}
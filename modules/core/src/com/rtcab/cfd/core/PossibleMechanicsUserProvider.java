package com.rtcab.cfd.core;

import com.haulmont.addon.bproc.provider.UserListProvider;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import com.rtcab.cfd.entity.FireDepartment;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component(PossibleMechanicsUserProvider.NAME)
public class PossibleMechanicsUserProvider implements UserListProvider {
    public static final String NAME = "cfd_PossibleMechanicsUserProvider";

    @Inject
    private BprocRuntimeService bprocRuntimeService;
    @Inject
    private DataManager dataManager;

    @Override
    public List<User> get(String executionId) {
        FireDepartment fireDepartment = (FireDepartment) bprocRuntimeService.getVariable(executionId, "fireDepartment");

        FireDepartment fireDepartmentWithEmployees = dataManager.reload(fireDepartment, "fireDepartment-with-employees");

        return fireDepartmentWithEmployees.mechanics();
    }
}
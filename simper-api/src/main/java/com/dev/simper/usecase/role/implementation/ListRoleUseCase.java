package com.dev.simper.usecase.role.implementation;

import com.dev.simper.entity.role.gateway.RoleGateway;
import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.usecase.role.contract.IListRoleUseCase;

import java.util.List;

public class ListRoleUseCase implements IListRoleUseCase {

    private final RoleGateway roleGateway;

    public ListRoleUseCase(RoleGateway roleGateway) {
        this.roleGateway = roleGateway;
    }

    @Override
    public List<RoleModel> execute() {
        return roleGateway.findAll();
    }
}

package com.dev.simper.usecase.role.implementation;

import com.dev.simper.entity.role.gateway.RoleGateway;
import com.dev.simper.usecase.role.contract.IDeleteRoleUseCase;

public class DeleteRoleUseCase implements IDeleteRoleUseCase {

    private final RoleGateway roleGateway;

    public DeleteRoleUseCase(RoleGateway roleGateway) {
        this.roleGateway = roleGateway;
    }

    @Override
    public void execute(Long id) {
        roleGateway.delete(id);
    }
}

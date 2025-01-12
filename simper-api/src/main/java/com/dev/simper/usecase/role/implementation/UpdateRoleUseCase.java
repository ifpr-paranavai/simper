package com.dev.simper.usecase.role.implementation;

import com.dev.simper.entity.role.gateway.RoleGateway;
import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.usecase.role.contract.IUpdateRoleUseCase;
import com.dev.simper.usecase.role.dto.IRoleDto;
import com.dev.simper.utils.ParseUtils;

public class UpdateRoleUseCase implements IUpdateRoleUseCase {

    private final RoleGateway roleGateway;

    public UpdateRoleUseCase(RoleGateway roleGateway) {
        this.roleGateway = roleGateway;
    }

    @Override
    public RoleModel execute(IRoleDto dto) {
        return roleGateway.update(ParseUtils.parse(dto, RoleModel.class));
    }
}

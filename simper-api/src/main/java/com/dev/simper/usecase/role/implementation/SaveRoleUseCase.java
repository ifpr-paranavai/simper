package com.dev.simper.usecase.role.implementation;

import com.dev.simper.entity.role.gateway.RoleGateway;
import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.usecase.role.contract.ISaveRoleUseCase;
import com.dev.simper.usecase.role.dto.IRoleDto;
import com.dev.simper.utils.ParseUtils;

public class SaveRoleUseCase implements ISaveRoleUseCase {

    private final RoleGateway roleGateway;

    public SaveRoleUseCase(RoleGateway roleGateway) {
        this.roleGateway = roleGateway;
    }

    @Override
    public RoleModel execute(IRoleDto dto) {
        return roleGateway.save(ParseUtils.parse(dto, RoleModel.class));
    }
}

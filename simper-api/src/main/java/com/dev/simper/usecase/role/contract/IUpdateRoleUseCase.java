package com.dev.simper.usecase.role.contract;

import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.usecase.role.dto.IRoleDto;

public interface IUpdateRoleUseCase {
    RoleModel execute(IRoleDto dto);
}

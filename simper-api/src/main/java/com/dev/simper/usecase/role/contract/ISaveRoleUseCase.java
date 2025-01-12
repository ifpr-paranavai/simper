package com.dev.simper.usecase.role.contract;

import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.usecase.role.dto.IRoleDto;

public interface ISaveRoleUseCase {
    RoleModel execute(IRoleDto dto);
}

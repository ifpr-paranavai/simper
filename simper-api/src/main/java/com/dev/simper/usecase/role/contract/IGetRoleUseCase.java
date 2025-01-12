package com.dev.simper.usecase.role.contract;

import com.dev.simper.entity.role.model.RoleModel;

public interface IGetRoleUseCase {
    RoleModel execute(Long id);
}

package com.dev.simper.usecase.role;

import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.usecase.role.dto.IRoleDto;

import java.util.List;

public interface IRoleUseCase {
    List<RoleModel> findAll();
    RoleModel findById(Long id);
    RoleModel save(IRoleDto dto);
    RoleModel update(IRoleDto dto);
    void delete(Long id);
}

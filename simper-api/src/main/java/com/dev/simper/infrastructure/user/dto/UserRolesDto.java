package com.dev.simper.infrastructure.user.dto;

import com.dev.simper.infrastructure.role.dto.RoleDto;
import com.dev.simper.usecase.user.dto.IUserRolesDto;

public record UserRolesDto(
    Long id,
    RoleDto role,
    UserDto user
) implements IUserRolesDto { }

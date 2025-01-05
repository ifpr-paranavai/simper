package com.dev.simper.usecase.user.dto;

import com.dev.simper.usecase.role.dto.IRoleDto;

public interface IUserRolesDto {
    Long id();
    IRoleDto role();
    IUserDto user();
}

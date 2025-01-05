package com.dev.simper.infrastructure.role.dto;

import com.dev.simper.usecase.role.dto.IRoleDto;

public record RoleDto(
    Long id,
    String name
) implements IRoleDto { }

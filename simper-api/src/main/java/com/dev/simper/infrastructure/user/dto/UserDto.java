package com.dev.simper.infrastructure.user.dto;

import java.util.List;

import com.dev.simper.usecase.user.dto.IUserDto;
import com.dev.simper.usecase.user.dto.IUserRolesDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
    Long id,
    @NotBlank(message = "{name.required}")
    String name,
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    String email,
    String status,
    String note,
    List<IUserRolesDto> userRoles
) implements IUserDto { }

package com.dev.simper.infrastructure.user.dto;

import com.dev.simper.usecase.user.dto.IUserPasswordResetDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserPasswordResetDto(
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    String email,
    @NotBlank(message = "{code.required}")
    String code,
    @NotBlank(message = "{password.required}")
    String password
) implements IUserPasswordResetDto {}

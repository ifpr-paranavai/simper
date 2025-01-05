package com.dev.simper.infrastructure.user.dto;

import com.dev.simper.usecase.user.dto.IUserPasswordResetRequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserPasswordResetRequestDto(
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    String email
) implements IUserPasswordResetRequestDto { }

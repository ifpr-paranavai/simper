package com.dev.simper.infrastructure.user.dto;

import com.dev.simper.usecase.user.dto.IUserRegisterDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserRegisterDto(
    @NotBlank(message = "{name.required}")
    String name,
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    String email
) implements IUserRegisterDto {}

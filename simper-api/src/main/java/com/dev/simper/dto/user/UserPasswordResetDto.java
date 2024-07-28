package com.dev.simper.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserPasswordResetDto {
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;

    @NotBlank(message = "{code.required}")
    private String code;

    @NotBlank(message = "{password.required}")
    private String password;
}

package com.dev.simper.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDto {
    @NotBlank(message = "{name.required}")
    private String name;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;
}

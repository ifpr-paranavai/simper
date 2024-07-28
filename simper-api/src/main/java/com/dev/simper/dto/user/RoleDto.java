package com.dev.simper.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleDto {
    private Long id;

    @NotBlank(message = "{name.required}")
    private String name;

    private String note;
}

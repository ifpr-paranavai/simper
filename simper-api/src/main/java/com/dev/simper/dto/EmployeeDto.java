package com.dev.simper.dto;

import com.dev.simper.model.Institution;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    @NotBlank(message = "{name.required}")
    private String name;
    @NotNull(message = "{institution.required}")
    private Institution institution;
}

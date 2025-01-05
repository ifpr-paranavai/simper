package com.dev.simper.infrastructure.employee.dto;

import com.dev.simper.infrastructure.configuration.database.schema.InstitutionSchema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;

    @NotBlank(message = "{name.required}")
    private String name;
    
    @NotNull(message = "{institution.required}")
    private InstitutionSchema institution;
}

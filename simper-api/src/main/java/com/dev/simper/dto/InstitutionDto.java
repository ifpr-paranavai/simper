package com.dev.simper.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InstitutionDto {
    private Long id;

    @NotBlank(message = "{name.required}")
    private String name;

    @NotBlank(message = "{domain.required}")
    private String domain;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String emailResponsible;

    @NotBlank(message = "{cnpj.required}")
    @CNPJ
    private String cnpj;
    
    private String logo;
}

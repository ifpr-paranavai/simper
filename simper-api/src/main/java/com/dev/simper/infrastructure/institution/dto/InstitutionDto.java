package com.dev.simper.infrastructure.institution.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import com.dev.simper.usecase.institution.dto.IInstitutionDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InstitutionDto(
    Long id,
    @NotBlank(message = "{name.required}")
    String name,
    @NotBlank(message = "{domain.required}")
    String domain,
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    String email,
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    String emailResponsible,
    @NotBlank(message = "{cnpj.required}")
    @CNPJ
    String cnpj,
    String logo
) implements IInstitutionDto { }

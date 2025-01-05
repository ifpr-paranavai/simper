package com.dev.simper.usecase.institution.dto;

public interface IInstitutionDto {
    Long id();
    String name();
    String domain();
    String email();
    String emailResponsible();
    String cnpj();
    String logo();
}

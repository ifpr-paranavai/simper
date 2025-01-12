package com.dev.simper.usecase.institution.implementation;

import com.dev.simper.entity.institution.gateway.InstitutionGateway;
import com.dev.simper.usecase.institution.contract.IDeleteInstitutionUseCase;

public class DeleteInstitutionUseCase implements IDeleteInstitutionUseCase {

    private final InstitutionGateway institutionGateway;

    public DeleteInstitutionUseCase(
        InstitutionGateway institutionGateway
    ) {
        this.institutionGateway = institutionGateway;
    }

    @Override
    public void execute(Long id) {
        institutionGateway.delete(id);
    }
}

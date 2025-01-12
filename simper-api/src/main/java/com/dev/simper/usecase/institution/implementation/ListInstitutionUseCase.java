package com.dev.simper.usecase.institution.implementation;

import java.util.List;

import com.dev.simper.entity.institution.gateway.InstitutionGateway;
import com.dev.simper.entity.institution.model.InstitutionModel;
import com.dev.simper.usecase.institution.contract.IListInstitutionUseCase;

public class ListInstitutionUseCase implements IListInstitutionUseCase {

    private final InstitutionGateway institutionGateway;

    public ListInstitutionUseCase(
        InstitutionGateway institutionGateway
    ) {
        this.institutionGateway = institutionGateway;
    }

    @Override
    public List<InstitutionModel> execute() {
        return institutionGateway.findAll();
    }
}

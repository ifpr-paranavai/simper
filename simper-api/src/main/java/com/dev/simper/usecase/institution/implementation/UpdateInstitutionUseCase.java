package com.dev.simper.usecase.institution.implementation;

import com.dev.simper.entity.institution.gateway.InstitutionGateway;
import com.dev.simper.entity.institution.model.InstitutionModel;
import com.dev.simper.usecase.institution.contract.IUpdateInstitutionUseCase;
import com.dev.simper.usecase.institution.dto.IInstitutionDto;
import com.dev.simper.utils.ParseUtils;

public class UpdateInstitutionUseCase implements IUpdateInstitutionUseCase {

    private final InstitutionGateway institutionGateway;

    public UpdateInstitutionUseCase(
        InstitutionGateway institutionGateway
    ) {
        this.institutionGateway = institutionGateway;
    }

    @Override
    public InstitutionModel execute(IInstitutionDto dto) {
        return institutionGateway.update(ParseUtils.parse(dto, InstitutionModel.class));
    }
}

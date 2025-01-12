package com.dev.simper.usecase.institution.implementation;

import com.dev.simper.entity.institution.gateway.InstitutionGateway;
import com.dev.simper.entity.institution.model.InstitutionModel;
import com.dev.simper.infrastructure.user.dto.UserRegisterDto;
import com.dev.simper.usecase.institution.contract.ISaveInstitutionUseCase;
import com.dev.simper.usecase.institution.dto.IInstitutionDto;
import com.dev.simper.usecase.user.contract.IUserAccountUseCase;
import com.dev.simper.utils.ParseUtils;

public class SaveInstitutionUseCase implements ISaveInstitutionUseCase {

    private final InstitutionGateway institutionGateway;
    private final IUserAccountUseCase iUserAccountUseCase;

    public SaveInstitutionUseCase(
        InstitutionGateway institutionGateway, 
        IUserAccountUseCase iUserAccountUseCase
    ) {
        this.institutionGateway = institutionGateway;
        this.iUserAccountUseCase = iUserAccountUseCase;
    }

    @Override
    public InstitutionModel execute(IInstitutionDto dto) {
        InstitutionModel institution = institutionGateway.save(ParseUtils.parse(dto, InstitutionModel.class));
        // creation user from responsible for institution
        iUserAccountUseCase.register(
            UserRegisterDto
            .builder()
            .name(dto.name())
            .email(dto.emailResponsible())
            .build()
        );
        return ParseUtils.parse(institution, InstitutionModel.class);
    }
}

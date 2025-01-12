package com.dev.simper.usecase.institution.implementation;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.dev.simper.entity.exception.ResourceNotFoundException;
import com.dev.simper.entity.institution.gateway.InstitutionGateway;
import com.dev.simper.entity.institution.model.InstitutionModel;
import com.dev.simper.usecase.institution.contract.IGetInstitutionUseCase;

public class GetInstitutionUseCase implements IGetInstitutionUseCase {

    private final InstitutionGateway institutionGateway;
    private final MessageSource messageSource;

    public GetInstitutionUseCase(
        InstitutionGateway institutionGateway, 
        MessageSource messageSource
    ) {
        this.institutionGateway = institutionGateway;
        this.messageSource = messageSource;
    }

    @Override
    public InstitutionModel execute(Long id) {
        return institutionGateway
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("error.institution.notfound", new Object[] { id }, Locale.getDefault())));
    }
}

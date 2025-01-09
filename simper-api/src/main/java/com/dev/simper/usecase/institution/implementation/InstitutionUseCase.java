package com.dev.simper.usecase.institution.implementation;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.dev.simper.entity.exception.ResourceNotFoundException;
import com.dev.simper.entity.institution.gateway.InstitutionGateway;
import com.dev.simper.entity.institution.model.InstitutionModel;
import com.dev.simper.infrastructure.user.dto.UserRegisterDto;
import com.dev.simper.usecase.institution.contract.IInstitutionUseCase;
import com.dev.simper.usecase.institution.dto.IInstitutionDto;
import com.dev.simper.usecase.user.contract.IUserAccountUseCase;
import com.dev.simper.utils.ParseUtils;

@Service
public class InstitutionUseCase implements IInstitutionUseCase {

    private final InstitutionGateway institutionGateway;
    private final IUserAccountUseCase iUserAccountUseCase;
    private final MessageSource messageSource;

    public InstitutionUseCase(InstitutionGateway institutionGateway, IUserAccountUseCase iUserAccountUseCase, MessageSource messageSource) {
        this.institutionGateway = institutionGateway;
        this.iUserAccountUseCase = iUserAccountUseCase;
        this.messageSource = messageSource;
    }

    @Override
    public InstitutionModel save(IInstitutionDto dto) {
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

    @Override
    public InstitutionModel findById(Long id) {
        return institutionGateway
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("error.institution.notfound", new Object[] { id }, Locale.getDefault())));
    }

    @Override
    public List<InstitutionModel> findAll() {
        return institutionGateway.findAll();
    }

    @Override
    public void delete(Long id) {
        institutionGateway.delete(id);
    }

    @Override
    public InstitutionModel update(IInstitutionDto dto) {
        return institutionGateway.update(ParseUtils.parse(dto, InstitutionModel.class));
    }
}

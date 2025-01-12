package com.dev.simper.usecase.institution.contract;

import com.dev.simper.entity.institution.model.InstitutionModel;

public interface IGetInstitutionUseCase {
    InstitutionModel execute(Long id);
}

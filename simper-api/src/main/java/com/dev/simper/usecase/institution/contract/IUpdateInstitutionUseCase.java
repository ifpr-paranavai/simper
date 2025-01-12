package com.dev.simper.usecase.institution.contract;

import com.dev.simper.entity.institution.model.InstitutionModel;
import com.dev.simper.usecase.institution.dto.IInstitutionDto;

public interface IUpdateInstitutionUseCase {
    InstitutionModel execute(IInstitutionDto dto);
}

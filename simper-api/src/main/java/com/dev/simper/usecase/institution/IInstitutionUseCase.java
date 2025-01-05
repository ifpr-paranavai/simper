package com.dev.simper.usecase.institution;

import java.util.List;

import com.dev.simper.entity.institution.model.InstitutionModel;
import com.dev.simper.usecase.institution.dto.IInstitutionDto;

public interface IInstitutionUseCase {
    InstitutionModel save(IInstitutionDto dto);
    InstitutionModel findById(Long id);
    List<InstitutionModel> findAll();
    InstitutionModel update(IInstitutionDto dto);
    void delete(Long id);
}

package com.dev.simper.usecase.institution.contract;

import java.util.List;

import com.dev.simper.entity.institution.model.InstitutionModel;

public interface IListInstitutionUseCase {
    List<InstitutionModel> execute();
}

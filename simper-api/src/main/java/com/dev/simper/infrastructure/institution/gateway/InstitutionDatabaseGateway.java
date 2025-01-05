package com.dev.simper.infrastructure.institution.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.simper.entity.institution.gateway.InstitutionGateway;
import com.dev.simper.entity.institution.model.InstitutionModel;
import com.dev.simper.infrastructure.configuration.database.repository.InstitutionRepository;
import com.dev.simper.infrastructure.configuration.database.schema.InstitutionSchema;
import com.dev.simper.utils.ParseUtils;

@Service
public class InstitutionDatabaseGateway implements InstitutionGateway {

    private final InstitutionRepository institutionRepository;

    public InstitutionDatabaseGateway(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public InstitutionModel save(InstitutionModel model) {
        return ParseUtils.parse(
            institutionRepository.saveAndFlush(ParseUtils.parse(model, InstitutionSchema.class)), 
            InstitutionModel.class
            );
    }

    @Override
    public Optional<InstitutionModel> findById(Long id) {
        return institutionRepository.findById(id)
            .map(schema -> ParseUtils.parse(schema, InstitutionModel.class));
    }

    @Override
    public List<InstitutionModel> findAll() {
        return ParseUtils.parse(
            institutionRepository.findAll(), 
            InstitutionModel.class
            );
    }

    @Override
    public void delete(Long id) {
        institutionRepository.deleteById(id);
    }

    @Override
    public InstitutionModel update(InstitutionModel model) {
        return ParseUtils.parse(
            institutionRepository.saveAndFlush(ParseUtils.parse(model, InstitutionSchema.class)), 
            InstitutionModel.class
            );
    }
}

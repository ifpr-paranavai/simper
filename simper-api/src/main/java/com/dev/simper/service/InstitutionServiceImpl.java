package com.dev.simper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.simper.dto.InstitutionDto;
import com.dev.simper.model.Institution;
import com.dev.simper.repository.InstitutionRepository;
import com.dev.simper.utils.ParseUtils;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public InstitutionDto save(InstitutionDto dto) {
        return ParseUtils.parse(
            institutionRepository.saveAndFlush(ParseUtils.parse(dto, Institution.class)), 
            InstitutionDto.class);
    }

    @Override
    public List<InstitutionDto> saveAll(List<InstitutionDto> dtos) {
        return ParseUtils.parse(
            institutionRepository.saveAllAndFlush(ParseUtils.parse(dtos, Institution.class)), 
            InstitutionDto.class);
    }

    @Override
    public InstitutionDto findById(Long id) {
        return ParseUtils.parse(
            institutionRepository.findById(id),
            InstitutionDto.class);
    }

    @Override
    public List<InstitutionDto> findAll() {
        return ParseUtils.parse(
            institutionRepository.findAll(),
            InstitutionDto.class);
    }

    @Override
    public void delete(Long id) {
        institutionRepository.deleteById(id);
    }

    @Override
    public InstitutionDto update(InstitutionDto dto) {
        return ParseUtils.parse(
            institutionRepository.saveAndFlush(ParseUtils.parse(dto, Institution.class)),
            InstitutionDto.class
        );
    }
}

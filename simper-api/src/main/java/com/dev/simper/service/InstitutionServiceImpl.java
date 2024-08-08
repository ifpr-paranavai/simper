package com.dev.simper.service;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.dev.simper.dto.InstitutionDto;
import com.dev.simper.dto.user.UserRegisterDto;
import com.dev.simper.exception.ResourceNotFoundException;
import com.dev.simper.model.Institution;
import com.dev.simper.repository.InstitutionRepository;
import com.dev.simper.utils.ParseUtils;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;

    private final UserAccountService userAccountService;

    private final MessageSource messageSource;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository, UserAccountService userAccountService, MessageSource messageSource) {
        this.institutionRepository = institutionRepository;
        this.userAccountService = userAccountService;
        this.messageSource = messageSource;
    }

    @Override
    public InstitutionDto save(InstitutionDto dto) {
        Institution institution = institutionRepository.saveAndFlush(ParseUtils.parse(dto, Institution.class));
        // creation user from responsible for institution
        userAccountService.register(
            UserRegisterDto
            .builder()
            .name(dto.getName())
            .email(dto.getEmailResponsible())
            .build()
        );
        return ParseUtils.parse(institution, InstitutionDto.class);
    }

    @Override
    public List<InstitutionDto> saveAll(List<InstitutionDto> dtos) {
        return ParseUtils.parse(
            institutionRepository.saveAllAndFlush(ParseUtils.parse(dtos, Institution.class)), 
            InstitutionDto.class);
    }

    @Override
    public InstitutionDto findById(Long id) {
        Institution institution = institutionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("institution.notfound", new Object[] { id }, Locale.getDefault())));
        return ParseUtils.parse(institution,InstitutionDto.class);
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

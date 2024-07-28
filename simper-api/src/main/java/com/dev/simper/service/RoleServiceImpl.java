package com.dev.simper.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.dev.simper.dto.user.RoleDto;
import com.dev.simper.exception.ResourceNotFoundException;
import com.dev.simper.model.Role;
import com.dev.simper.repository.RoleRepository;
import com.dev.simper.utils.ParseUtils;

import java.util.List;
import java.util.Locale;

@Service
public class  RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    private MessageSource messageSource;

    public RoleServiceImpl(RoleRepository roleRepository, MessageSource messageSource) {
        this.roleRepository = roleRepository;
        this.messageSource = messageSource;
    }

    @Override
    public List<RoleDto> findAll() {
        return ParseUtils.parse(roleRepository.findAll(), RoleDto.class);
    }

    @Override
    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("role.notfound", new Object[] { id }, Locale.getDefault())));
        return ParseUtils.parse(role, RoleDto.class);
    }

    @Override
    public RoleDto save(RoleDto dto) {
        return ParseUtils.parse(
            roleRepository.saveAndFlush(ParseUtils.parse(dto, Role.class)), 
            RoleDto.class);
    }

    @Override
    public List<RoleDto> saveAll(List<RoleDto> dtos) {
        return ParseUtils.parse(
            roleRepository.saveAllAndFlush(ParseUtils.parse(dtos, Role.class)), 
            RoleDto.class);
    }

    @Override
    public RoleDto update(RoleDto dto) {
        return ParseUtils.parse(
            ParseUtils.parse(dto, Role.class), 
            RoleDto.class);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}

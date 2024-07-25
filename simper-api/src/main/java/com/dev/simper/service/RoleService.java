package com.dev.simper.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.dev.simper.dto.user.RoleDto;
import com.dev.simper.exception.ResourceNotFoundException;
import com.dev.simper.model.Role;
import com.dev.simper.repository.RoleRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;



@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("role.notfound", new Object[] { id }, Locale.getDefault())));
        return modelMapper.map(role, RoleDto.class);
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        Role savedRole = roleRepository.save(role);
        return modelMapper.map(savedRole, RoleDto.class);
    }

    public RoleDto updateRole(RoleDto roleDto) {
        Role role = roleRepository.findById(roleDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("role.notfound", new Object[] { roleDto.getId() },
                                Locale.getDefault())));
        modelMapper.map(roleDto, role);
        Role updatedRole = roleRepository.save(role);
        return modelMapper.map(updatedRole, RoleDto.class);
    }

    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("role.notfound", new Object[] { id }, Locale.getDefault())));
        roleRepository.delete(role);
    }

}

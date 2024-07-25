package com.dev.simper.dto.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.simper.model.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleDto {
    private Long id;
    @NotBlank(message = "{name.required}")
    private String name;
    private String note;

 @Autowired
    private static ModelMapper modelMapper;

    public static RoleDto fromEntity(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    public Role toEntity() {
        return modelMapper.map(this, Role.class);
    }
}

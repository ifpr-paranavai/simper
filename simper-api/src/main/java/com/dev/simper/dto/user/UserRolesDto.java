package com.dev.simper.dto.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.simper.model.Role;
import com.dev.simper.model.User;
import com.dev.simper.model.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserRolesDto {
    private Long id;
    private Role role;
    @JsonIgnore
    private User user;


    @Autowired
    private static ModelMapper modelMapper;

    public static UserRolesDto fromEntity(UserRoles user) {
        return modelMapper.map(user, UserRolesDto.class);
    }

    public UserRoles toEntity() {
        return modelMapper.map(this, UserRoles.class);
    }

}

package com.dev.simper.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserRolesDto {
    private Long id;

    private RoleDto role;

    @JsonIgnore
    private UserDto user;
}

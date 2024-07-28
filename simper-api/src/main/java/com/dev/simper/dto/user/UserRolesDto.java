package com.dev.simper.dto.user;

import com.dev.simper.model.Role;
import com.dev.simper.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserRolesDto {
    private Long id;

    private Role role;

    @JsonIgnore
    private User user;
}

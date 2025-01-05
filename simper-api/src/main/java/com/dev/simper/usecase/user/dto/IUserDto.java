package com.dev.simper.usecase.user.dto;

import java.util.List;

public interface IUserDto {
    Long id();
    String name();
    String email();
    String status();
    String note();
    List<IUserRolesDto> userRoles();
}

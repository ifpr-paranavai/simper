package com.dev.simper.entity.user.gateway;

import org.springframework.http.ResponseEntity;

import com.dev.simper.usecase.user.dto.IUserRegisterDto;

public interface UserAccountGateway {
    ResponseEntity<String> register(IUserRegisterDto dto);
}

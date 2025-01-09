package com.dev.simper.usecase.auth.contract;

import org.springframework.http.ResponseEntity;

import com.dev.simper.infrastructure.auth.dto.AuthRequestDto;

public interface IAuthUseCase {
    ResponseEntity<?> createAuthenticationToken(AuthRequestDto dto);
}

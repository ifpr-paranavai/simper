package com.dev.simper.service;

import org.springframework.http.ResponseEntity;

import com.dev.simper.dto.AuthRequestDto;

public interface AuthService {
    ResponseEntity<?> createAuthenticationToken(AuthRequestDto dto);
}

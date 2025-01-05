package com.dev.simper.infrastructure.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.simper.infrastructure.auth.dto.AuthRequestDto;
import com.dev.simper.usecase.auth.AuthUseCase;


@RestController
@RequestMapping("/v1/auth")
@CrossOrigin
public class AuthController {

    private final AuthUseCase authService;

    public AuthController(AuthUseCase authServiceImpl) {
        this.authService = authServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestDto dto) throws Exception {
        return authService.createAuthenticationToken(dto);
    }
}

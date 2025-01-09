package com.dev.simper.infrastructure.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.simper.infrastructure.auth.dto.AuthRequestDto;
import com.dev.simper.usecase.auth.contract.IAuthUseCase;


@RestController
@RequestMapping("/v1/auth")
@CrossOrigin
public class AuthController {

    private final IAuthUseCase iAuthUseCase;

    public AuthController(IAuthUseCase iAuthUseCase) {
        this.iAuthUseCase = iAuthUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestDto dto) throws Exception {
        return iAuthUseCase.createAuthenticationToken(dto);
    }
}

package com.dev.simper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.simper.dto.AuthRequestDto;
import com.dev.simper.service.AuthServiceImpl;


@RestController
@RequestMapping("/v1/auth")
@CrossOrigin
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authService = authServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestDto dto) throws Exception {
        return authService.createAuthenticationToken(dto);
    }
}

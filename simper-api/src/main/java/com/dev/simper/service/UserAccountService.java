package com.dev.simper.service;

import org.springframework.http.ResponseEntity;

import com.dev.simper.dto.user.UserPasswordResetDto;
import com.dev.simper.dto.user.UserRegisterDto;

import jakarta.transaction.Transactional;

public interface UserAccountService {
    @Transactional
    ResponseEntity<String> register(UserRegisterDto dto);

    @Transactional
    ResponseEntity<String> setPassword(UserPasswordResetDto dto);

    @Transactional
    ResponseEntity<String> sendVerificationCode(String email);
}

package com.dev.simper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.simper.dto.user.UserPasswordResetDto;
import com.dev.simper.dto.user.UserPasswordResetRequestDto;
import com.dev.simper.dto.user.UserRegisterDto;
import com.dev.simper.service.UserAccountServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/user-account")
@CrossOrigin
public class UserAccountController {

    private UserAccountServiceImpl userAccountService;

    public UserAccountController(
        UserAccountServiceImpl userAccountService
    ) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto dto) {
        return userAccountService.register(dto);
    }

    @PostMapping("/set-password")
    public ResponseEntity<String> setPassword(@Valid @RequestBody UserPasswordResetDto dto) {
        return userAccountService.setPassword(dto);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody UserPasswordResetRequestDto dto) {
        return userAccountService.sendVerificationCode(dto.getEmail());
    }
}

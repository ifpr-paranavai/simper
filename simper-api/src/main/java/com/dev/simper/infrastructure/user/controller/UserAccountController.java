package com.dev.simper.infrastructure.user.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.simper.entity.exception.EmailSendException;
import com.dev.simper.entity.exception.ResourceNotFoundException;
import com.dev.simper.infrastructure.user.dto.UserPasswordResetDto;
import com.dev.simper.infrastructure.user.dto.UserPasswordResetRequestDto;
import com.dev.simper.usecase.user.IUserAccountUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/user-account")
@CrossOrigin
public class UserAccountController {

    private final IUserAccountUseCase iUserAccountUseCase;
    private final MessageSource messageSource;

    public UserAccountController(IUserAccountUseCase iUserAccountUseCase, MessageSource messageSource) {
        this.iUserAccountUseCase = iUserAccountUseCase;
        this.messageSource = messageSource;
    }

    @PostMapping("/set-password")
    public ResponseEntity<String> setPassword(@Valid @RequestBody UserPasswordResetDto dto) {
        try {
            iUserAccountUseCase.setPassword(dto);
            return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("success.changed.password", null, Locale.getDefault()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody UserPasswordResetRequestDto dto) {
        try {
            iUserAccountUseCase.changePassword(dto.email());
            return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("success.send.code.email", null, Locale.getDefault()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EmailSendException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

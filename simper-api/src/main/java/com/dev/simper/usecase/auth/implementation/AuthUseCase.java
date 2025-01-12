package com.dev.simper.usecase.auth.implementation;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.dev.simper.infrastructure.auth.dto.AuthRequestDto;
import com.dev.simper.infrastructure.auth.dto.AuthResponseDto;
import com.dev.simper.infrastructure.configuration.security.JwtTokenUtil;
import com.dev.simper.usecase.auth.contract.IAuthUseCase;
import com.dev.simper.usecase.user.contract.ILoadUserDetailsUseCase;

public class AuthUseCase implements IAuthUseCase {

    private final AuthenticationManager authenticationManager;
    private final ILoadUserDetailsUseCase iLoadUserDetailsUseCase;
    private final JwtTokenUtil jwtTokenUtil;
    private final MessageSource messageSource;

    public AuthUseCase(
        AuthenticationManager authenticationManager,
        ILoadUserDetailsUseCase iLoadUserDetailsUseCase,
        JwtTokenUtil jwtTokenUtil,
        MessageSource messageSource
    ) {
        this.authenticationManager = authenticationManager;
        this.iLoadUserDetailsUseCase = iLoadUserDetailsUseCase;
        this.jwtTokenUtil = jwtTokenUtil;
        this.messageSource = messageSource;
    }

    @Override
    public ResponseEntity<?> createAuthenticationToken(AuthRequestDto dto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
            final UserDetails userDetails = iLoadUserDetailsUseCase.execute(dto.getEmail());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            
            return ResponseEntity.ok(new AuthResponseDto(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(messageSource.getMessage("error.invalid.credentials", null, Locale.getDefault()));
        }
    }
}

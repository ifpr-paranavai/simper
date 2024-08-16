package com.dev.simper.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dev.simper.dto.AuthRequestDto;
import com.dev.simper.dto.AuthResponseDto;
import com.dev.simper.security.JwtTokenUtil;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private JwtTokenUtil jwtTokenUtil;

    private final MessageSource messageSource;

    public AuthServiceImpl(
        AuthenticationManager authenticationManager,
        UserDetailsServiceImpl userDetailsServiceImpl,
        JwtTokenUtil jwtTokenUtil,
        MessageSource messageSource
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsServiceImpl;
        this.jwtTokenUtil = jwtTokenUtil;
        this.messageSource = messageSource;
    }

    @Override
    public ResponseEntity<?> createAuthenticationToken(AuthRequestDto dto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            
            return ResponseEntity.ok(new AuthResponseDto(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(messageSource.getMessage("error.invalid.credentials", null, Locale.getDefault()));
        }
    }
}

package com.dev.simper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.simper.dto.AuthRequestDto;
import com.dev.simper.dto.AuthResponseDto;
import com.dev.simper.dto.user.UserPasswordResetDto;
import com.dev.simper.dto.user.UserPasswordResetRequestDto;
import com.dev.simper.dto.user.UserRegisterDto;
import com.dev.simper.security.JwtTokenUtil;
import com.dev.simper.service.UserAccountServiceImpl;
import com.dev.simper.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/user-account")
@CrossOrigin
public class UserAccountController {

    private UserAccountServiceImpl userAccountService;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsServiceImpl userDetailsService;

    public UserAccountController(
        UserAccountServiceImpl userAccountService,
        AuthenticationManager authenticationManager,
        JwtTokenUtil jwtTokenUtil,
        UserDetailsServiceImpl userDetailsServiceImpl
    ) {
        this.userAccountService = userAccountService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsServiceImpl;
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
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody UserPasswordResetRequestDto dto) {
        return userAccountService.sendVerificationCode(dto.getEmail());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestDto authRequest) throws Exception {
        try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseDto(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}

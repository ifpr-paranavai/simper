package com.dev.simper.infrastructure.auth.dto;

import lombok.Getter;

@Getter
public class AuthRequestDto {
    private String email;
    
    private String password;
}

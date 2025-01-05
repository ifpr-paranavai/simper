package com.dev.simper.usecase.user.dto;

public interface IUserPasswordResetDto {
    String email();
    String code();
    String password();
}

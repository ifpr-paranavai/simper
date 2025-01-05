package com.dev.simper.usecase.user;

import com.dev.simper.usecase.user.dto.IUserPasswordResetDto;
import com.dev.simper.usecase.user.dto.IUserRegisterDto;

public interface IUserAccountUseCase {
    void register(IUserRegisterDto dto);
    void setPassword(IUserPasswordResetDto dto);
    void changePassword(String email);
}

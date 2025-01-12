package com.dev.simper.usecase.user.contract;

import com.dev.simper.usecase.user.dto.IUserRegisterDto;

public interface IRegisterUserAccountUseCase {
    void execute(IUserRegisterDto dto);
}

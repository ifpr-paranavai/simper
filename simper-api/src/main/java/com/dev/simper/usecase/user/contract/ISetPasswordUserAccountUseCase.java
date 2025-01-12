package com.dev.simper.usecase.user.contract;

import com.dev.simper.usecase.user.dto.IUserPasswordResetDto;

public interface ISetPasswordUserAccountUseCase {
    void execute(IUserPasswordResetDto dto);
}

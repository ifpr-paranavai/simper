package com.dev.simper.usecase.user.contract;

import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.usecase.user.dto.IUserDto;

public interface IUpdatedUserUseCase {
    UserModel execute(IUserDto dto);
}

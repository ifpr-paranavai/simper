package com.dev.simper.usecase.user.contract;

import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.infrastructure.user.dto.UserDto;

public interface ISaveUserUseCase {
    UserModel execute(UserDto dto);
}

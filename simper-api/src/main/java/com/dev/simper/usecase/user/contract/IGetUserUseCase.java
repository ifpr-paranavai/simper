package com.dev.simper.usecase.user.contract;

import com.dev.simper.entity.user.model.UserModel;

public interface IGetUserUseCase {
    UserModel execute(Long id);
}

package com.dev.simper.usecase.user.contract;

import com.dev.simper.entity.user.model.UserModel;

import java.util.List;

public interface IListUserUseCase {
    List<UserModel> execute();
}

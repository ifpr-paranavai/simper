package com.dev.simper.usecase.user.implementation;

import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.usecase.user.contract.IListUserUseCase;

import java.util.List;

public class ListUserUseCase implements IListUserUseCase {

    private final UserGateway userGateway;

    public ListUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserModel> execute() {
        return userGateway.findAll();
    }
}

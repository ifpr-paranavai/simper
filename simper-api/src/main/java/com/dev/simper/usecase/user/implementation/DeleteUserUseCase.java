package com.dev.simper.usecase.user.implementation;

import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.usecase.user.contract.IDeleteUserUseCase;

public class DeleteUserUseCase implements IDeleteUserUseCase {

    private final UserGateway userGateway;

    public DeleteUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void execute(Long id) {
        userGateway.delete(id);
    }
}

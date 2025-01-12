package com.dev.simper.usecase.user.implementation;

import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.usecase.user.contract.IUpdatedUserUseCase;
import com.dev.simper.usecase.user.dto.IUserDto;
import com.dev.simper.utils.ParseUtils;

public class UpdateUserUseCase implements IUpdatedUserUseCase {

    private final UserGateway userGateway;

    public UpdateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserModel execute(IUserDto dto) {
        return userGateway.update(ParseUtils.parse(dto, UserModel.class));
    }
}

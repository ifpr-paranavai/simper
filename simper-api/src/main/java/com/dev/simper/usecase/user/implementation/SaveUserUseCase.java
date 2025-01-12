package com.dev.simper.usecase.user.implementation;

import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.infrastructure.user.dto.UserDto;
import com.dev.simper.usecase.user.contract.ISaveUserUseCase;
import com.dev.simper.utils.ParseUtils;

public class SaveUserUseCase implements ISaveUserUseCase {

    private final UserGateway userGateway;

    public SaveUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserModel execute(UserDto dto) {
        return userGateway.save(ParseUtils.parse(dto, UserModel.class));
    }
}

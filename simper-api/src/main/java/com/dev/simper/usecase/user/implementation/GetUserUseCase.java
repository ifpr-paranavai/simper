package com.dev.simper.usecase.user.implementation;

import org.springframework.context.MessageSource;

import com.dev.simper.entity.exception.ResourceNotFoundException;
import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.usecase.user.contract.IGetUserUseCase;

import java.util.Locale;

public class GetUserUseCase implements IGetUserUseCase {

    private final UserGateway userGateway;
    private final MessageSource messageSource;

    public GetUserUseCase(
        UserGateway userGateway, 
        MessageSource messageSource
    ) {
        this.userGateway = userGateway;
        this.messageSource = messageSource;
    }

    @Override
    public UserModel execute(Long id) {
        return userGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.user.notfound", new Object[]{id}, Locale.getDefault())));
    }
}

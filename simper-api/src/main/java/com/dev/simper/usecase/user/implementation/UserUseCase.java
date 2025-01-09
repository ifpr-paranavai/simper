package com.dev.simper.usecase.user.implementation;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.dev.simper.entity.exception.ResourceNotFoundException;
import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.infrastructure.user.dto.UserDto;
import com.dev.simper.usecase.user.contract.IUserUseCase;
import com.dev.simper.usecase.user.dto.IUserDto;
import com.dev.simper.utils.ParseUtils;

import java.util.List;
import java.util.Locale;

@Service
public class UserUseCase implements IUserUseCase {

    private final UserGateway userGateway;
    private final MessageSource messageSource;

    public UserUseCase(UserGateway userGateway, MessageSource messageSource) {
        this.userGateway = userGateway;
        this.messageSource = messageSource;
    }

    @Override
    public List<UserModel> findAll() {
        return userGateway.findAll();
    }

    @Override
    public UserModel findById(Long id) {
        return userGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.user.notfound", new Object[]{id}, Locale.getDefault())));
    }

    @Override
    public UserModel save(UserDto dto) {
        return userGateway.save(ParseUtils.parse(dto, UserModel.class));
    }

    @Override
    public UserModel update(IUserDto dto) {
        return userGateway.update(ParseUtils.parse(dto, UserModel.class));
    }

    @Override
    public void delete(Long id) {
        userGateway.delete(id);
    }
}

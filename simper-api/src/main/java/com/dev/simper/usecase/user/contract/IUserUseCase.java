package com.dev.simper.usecase.user.contract;

import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.infrastructure.user.dto.UserDto;
import com.dev.simper.usecase.user.dto.IUserDto;

import java.util.List;

public interface IUserUseCase {
    List<UserModel> findAll();
    UserModel findById(Long id);
    UserModel save(UserDto dto);
    UserModel update(IUserDto dto);
    void delete(Long id);
}

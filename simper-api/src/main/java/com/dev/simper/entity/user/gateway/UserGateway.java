package com.dev.simper.entity.user.gateway;

import java.util.Optional;

import com.dev.simper.entity.generic.model.GenericGateway;
import com.dev.simper.entity.user.model.UserModel;

public interface UserGateway extends GenericGateway<UserModel> {
    Optional<UserModel> findByEmail(String email);
}

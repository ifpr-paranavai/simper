package com.dev.simper.infrastructure.user.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.infrastructure.configuration.database.repository.UserRepository;
import com.dev.simper.infrastructure.configuration.database.schema.UserSchema;
import com.dev.simper.utils.ParseUtils;

@Service
public class UserDatabaseGateway implements UserGateway {

    private final UserRepository userRepository;

    UserDatabaseGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserModel> findAll() {
        return ParseUtils.parse(
            userRepository.findAll(), 
            UserModel.class
            );
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id)
            .map(schema -> ParseUtils.parse(schema, UserModel.class));
    }

    @Override
    public UserModel save(UserModel model) {
        return ParseUtils.parse(
            userRepository.saveAndFlush(ParseUtils.parse(model, UserSchema.class)), 
            UserModel.class
            );
    }

    @Override
    public UserModel update(UserModel model) {
        return ParseUtils.parse(
            userRepository.saveAndFlush(ParseUtils.parse(model, UserSchema.class)), 
            UserModel.class
            );
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email)
            .map(schema -> ParseUtils.parse(schema, UserModel.class));
    }
}
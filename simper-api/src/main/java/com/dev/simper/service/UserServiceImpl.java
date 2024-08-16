package com.dev.simper.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.dev.simper.dto.user.UserDto;
import com.dev.simper.exception.ResourceNotFoundException;
import com.dev.simper.model.User;
import com.dev.simper.repository.UserRepository;
import com.dev.simper.utils.ParseUtils;

import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final MessageSource messageSource;

    public UserServiceImpl(UserRepository userRepository, MessageSource messageSource) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
    }

    @Override
    public List<UserDto> findAll() {
        return ParseUtils.parse(userRepository.findAll(), UserDto.class);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.user.notfound", new Object[] { id }, Locale.getDefault())));
        return ParseUtils.parse(user, UserDto.class);
    }

    @Override
    public UserDto save(UserDto dto) {
        return ParseUtils.parse(
            userRepository.saveAndFlush(ParseUtils.parse(dto, User.class)), 
            UserDto.class);
    }

    @Override
    public List<UserDto> saveAll(List<UserDto> dtos) {
        return ParseUtils.parse(
            userRepository.saveAllAndFlush(ParseUtils.parse(dtos, User.class)), 
            UserDto.class);
    }

    @Override
    public UserDto update(UserDto dto) {
        return ParseUtils.parse(
            userRepository.saveAndFlush(ParseUtils.parse(dto, User.class)), 
            UserDto.class);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

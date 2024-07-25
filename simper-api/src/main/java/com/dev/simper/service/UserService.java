package com.dev.simper.service;

import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.dev.simper.dto.user.UserDto;
import com.dev.simper.exception.ResourceNotFoundException;
import com.dev.simper.model.Role;
import com.dev.simper.model.User;
import com.dev.simper.repository.RoleRepository;
import com.dev.simper.repository.UserRepository;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("user.notfound", new Object[] { id }, Locale.getDefault())));
        return modelMapper.map(user, UserDto.class);
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("user.notfound", new Object[] { userDto.getId() },
                                Locale.getDefault())));
        modelMapper.map(userDto, user);

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("user.notfound", new Object[] { id }, Locale.getDefault())));
        userRepository.delete(user);
    }

}

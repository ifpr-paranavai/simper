package com.dev.simper.dto.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.simper.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDto {
    @NotBlank(message = "{name.required}")
    private String name;
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;

        @Autowired
    private static ModelMapper modelMapper;

    public static UserDto fromEntity(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User toEntity() {
        return modelMapper.map(this, User.class);
    }

}

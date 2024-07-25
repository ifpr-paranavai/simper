package com.dev.simper.dto.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.simper.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserPasswordResetDto {
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    private String email;
    @NotBlank(message = "{code.required}")
    private String code;
    @NotBlank(message = "{password.required}")
    private String password;

        @Autowired
    private static ModelMapper modelMapper;

    public static UserDto fromEntity(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User toEntity() {
        return modelMapper.map(this, User.class);
    }

}

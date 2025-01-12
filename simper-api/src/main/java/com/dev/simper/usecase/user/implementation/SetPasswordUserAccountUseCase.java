package com.dev.simper.usecase.user.implementation;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dev.simper.entity.exception.ResourceNotFoundException;
import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.usecase.user.contract.ISetPasswordUserAccountUseCase;
import com.dev.simper.usecase.user.dto.IUserPasswordResetDto;

import jakarta.transaction.Transactional;

public class SetPasswordUserAccountUseCase implements ISetPasswordUserAccountUseCase {

    private final UserGateway userGateway;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;

    public SetPasswordUserAccountUseCase(
        UserGateway userGateway,
        MessageSource messageSource,
        PasswordEncoder passwordEncoder
    ) {
        this.userGateway = userGateway;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void execute(IUserPasswordResetDto dto) {
        UserModel user = userGateway.findByEmail(dto.email())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.user.notfound.username", new Object[] { dto.email() }, Locale.getDefault())));

        if (!isValidVerificationCode(user, dto.code())) {
            throw new IllegalArgumentException(messageSource.getMessage("error.password.code", null, Locale.getDefault()));
        }

        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setStatus("ACTIVE");
        clearVerificationCode(user);
        userGateway.update(user);
    } 

    private boolean isValidVerificationCode(UserModel model, String verificationCode) {
        return model.getVerificationCode().equals(verificationCode)
                && model.getVerificationExpiry().isAfter(LocalDateTime.now());
    }

    private void clearVerificationCode(UserModel model) {
        model.setVerificationCode(null);
        model.setVerificationExpiry(null);
    }
}

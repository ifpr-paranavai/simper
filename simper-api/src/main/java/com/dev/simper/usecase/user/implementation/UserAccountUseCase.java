package com.dev.simper.usecase.user.implementation;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.context.Context;

import com.dev.simper.entity.exception.EmailSendException;
import com.dev.simper.entity.exception.ResourceNotFoundException;
import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.usecase.email.contract.IEmailUseCase;
import com.dev.simper.usecase.user.contract.IUserAccountUseCase;
import com.dev.simper.usecase.user.dto.IUserPasswordResetDto;
import com.dev.simper.usecase.user.dto.IUserRegisterDto;
import com.dev.simper.utils.ParseUtils;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

public class UserAccountUseCase implements IUserAccountUseCase {

    private final UserGateway userGateway;
    private final IEmailUseCase iEmailUseCase;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;

    public UserAccountUseCase(
        UserGateway userGateway,
        IEmailUseCase iEmailUseCase,
        MessageSource messageSource,
        PasswordEncoder passwordEncoder
    ) {
        this.userGateway = userGateway;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
        this.iEmailUseCase = iEmailUseCase;
    }

    @Override
    public void register(IUserRegisterDto dto) {
        UserModel user = ParseUtils.parse(dto, UserModel.class);
        user.setStatus("PENDING");
        generateAndSendVerificationCode(user);
    }

    @Override
    @Transactional
    public void setPassword(IUserPasswordResetDto dto) {
        UserModel user = userGateway.findByEmail(dto.email())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.user.notfound.username", new Object[] { dto.email() }, Locale.getDefault())));

        if (!isValidVerificationCode(user, dto.code())) {
            throw new IllegalArgumentException(messageSource.getMessage("error.password.code", null, Locale.getDefault()));
        }

        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setStatus("ACTIVE");
        clearVerificationCode(user);
        userGateway.save(user);
    }

    @Override
    @Transactional
    public void changePassword(String email) {
        UserModel user = userGateway.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.user.notfound.username", new Object[] { email }, Locale.getDefault())));
        generateAndSendVerificationCode(user);
    }

    private void generateAndSendVerificationCode(UserModel model) {
        try {
            sendEmailVerificationCode(model, generateVerificationCode());
            userGateway.save(model);
        } catch (MessagingException e) {
            throw new EmailSendException(messageSource.getMessage("error.send.code.email", null, Locale.getDefault()), e);
        }
    }

    private String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(999999));
    }

    private void sendEmailVerificationCode(UserModel model, String verificationCode) throws MessagingException {
        model.setVerificationCode(verificationCode);
        model.setVerificationExpiry(LocalDateTime.now().plusMinutes(15));
        Context context = new Context();
        context.setVariable("code", verificationCode);
        context.setVariable("name", model.getName());

        iEmailUseCase.sendTemplateEmail(model.getEmail(), "Password definition", context, "userPasswordUpdate");
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

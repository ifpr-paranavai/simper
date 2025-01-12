package com.dev.simper.usecase.user.implementation;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

import org.springframework.context.MessageSource;
import org.thymeleaf.context.Context;

import com.dev.simper.entity.exception.EmailSendException;
import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.usecase.email.contract.ISendTemplateEmailUseCase;
import com.dev.simper.usecase.user.contract.IRegisterUserAccountUseCase;
import com.dev.simper.usecase.user.dto.IUserRegisterDto;
import com.dev.simper.utils.ParseUtils;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

public class RegisterUserAccountUseCase implements IRegisterUserAccountUseCase {

    private final UserGateway userGateway;
    private final ISendTemplateEmailUseCase iSendTemplateEmailUseCase;
    private final MessageSource messageSource;

    public RegisterUserAccountUseCase(
        UserGateway userGateway,
        ISendTemplateEmailUseCase iSendTemplateEmailUseCase,
        MessageSource messageSource
    ) {
        this.userGateway = userGateway;
        this.messageSource = messageSource;
        this.iSendTemplateEmailUseCase = iSendTemplateEmailUseCase;
    }

    @Override
    @Transactional
    public void execute(IUserRegisterDto dto) {
        UserModel user = ParseUtils.parse(dto, UserModel.class);
        user.setStatus("PENDING");
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

        iSendTemplateEmailUseCase.execute(model.getEmail(), "Password definition", context, "userPasswordUpdate");
    }
}

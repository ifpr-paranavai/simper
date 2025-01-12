package com.dev.simper.usecase.email.contract;

import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;

public interface ISendTemplateEmailUseCase {
    public void execute(String to, String subject, Context emailVariables, String templateFileName) throws MessagingException;
}

package com.dev.simper.usecase.email.contract;

import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;

public interface IEmailUseCase {
    public void sendSimpleEmail(String to, String subject, String text);
    public void sendTemplateEmail(String to, String subject, Context emailVariables, String templateFileName) throws MessagingException;
}

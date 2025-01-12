package com.dev.simper.usecase.email.implementation;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.dev.simper.usecase.email.contract.ISendSimpleEmailUseCase;

public class SendSimpleEmailUseCase implements ISendSimpleEmailUseCase {

    private final JavaMailSender mailSender;

    public SendSimpleEmailUseCase(
        JavaMailSender javaMailSender
    ) {
        this.mailSender = javaMailSender;
    }

    /**
     * Sends a simple email with the provided recipient, subject, and text.
     *
     * @param to      the recipient's email address
     * @param subject the email subject
     * @param text    the email content
     */
    public void execute(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}

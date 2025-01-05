package com.dev.simper.usecase.email;

import org.springframework.stereotype.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailUseCase implements IEmailUseCase {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailUseCase(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.mailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * Sends a simple email with the provided recipient, subject, and text.
     *
     * @param to      the recipient's email address
     * @param subject the email subject
     * @param text    the email content
     */
    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    /**
     * Sends an email using a template with the provided recipient, subject, email variables, and template file name.
     *
     * @param to               the recipient's email address
     * @param subject          the email subject
     * @param emailVariables   the email variables to be used in the template - org.thymeleaf.context.Context
     * @param templateFileName the name of the HTML template file
     * @throws MessagingException if an error occurs while sending the email
     */
    public void sendTemplateEmail(String to, String subject, Context emailVariables, String templateFileName)
            throws MessagingException {

        String process = templateEngine.process(templateFileName, emailVariables);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(process, true);

        mailSender.send(message);
    }
}

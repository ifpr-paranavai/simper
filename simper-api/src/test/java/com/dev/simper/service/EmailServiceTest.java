package com.dev.simper.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.dev.simper.service.EmailService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private TemplateEngine templateEngine;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendTemplateEmail() throws MessagingException {
        // Arrange
        String to = "test@example.com";
        String subject = "Test Subject";
        Context emailVariables = new Context();
        emailVariables.setVariable("name", "Test User");
        String templateFileName = "testTemplate";
        String processedTemplate = "<p>Hello, Test User!</p>";

        when(templateEngine.process(templateFileName, emailVariables)).thenReturn(processedTemplate);

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        ArgumentCaptor<String> toCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);

        // Act
        emailService.sendTemplateEmail(to, subject, emailVariables, templateFileName);

        // Assert
        verify(mailSender).send(mimeMessage);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(toCaptor.capture());
        helper.setSubject(subjectCaptor.capture());
        helper.setText(textCaptor.capture(), eq(true));

        assertEquals(to, toCaptor.getValue());
        assertEquals(subject, subjectCaptor.getValue());
        assertEquals(processedTemplate, textCaptor.getValue());
    }
}

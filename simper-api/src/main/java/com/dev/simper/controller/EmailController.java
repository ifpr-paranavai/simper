package com.dev.simper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import com.dev.simper.service.EmailService;

import jakarta.mail.MessagingException;



@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        try {
            Context variables=new Context();
            variables.setVariable("name", "Frank");
            variables.setVariable("code", "123456");
            emailService.sendTemplateEmail("steorelio@gmail.com", "Teste", variables, "emailUserValidation");
            return "Email enviado com sucesso!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
}

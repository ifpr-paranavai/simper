package com.dev.simper.service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.dev.simper.dto.user.UserPasswordResetDto;
import com.dev.simper.dto.user.UserRegisterDto;
import com.dev.simper.exception.EmailSendException;
import com.dev.simper.exception.ResourceNotFoundException;
import com.dev.simper.model.User;
import com.dev.simper.repository.UserRepository;
import com.dev.simper.utils.ParseUtils;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private UserRepository userRepository;

    private MessageSource messageSource;

    private PasswordEncoder passwordEncoder;

    private EmailService emailService;

    public UserAccountServiceImpl(
        UserRepository userRepository,
        MessageSource messageSource,
        PasswordEncoder passwordEncoder,
        EmailService emailService
    ) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public ResponseEntity<String> register(UserRegisterDto dto) {
        User user = ParseUtils.parse(dto, User.class);
        user.setStatus("PENDING");
        try {
            generateAndSendVerificationCode(user);
            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(messageSource.getMessage("", null, Locale.getDefault()), e);
        }
        return ResponseEntity.ok(messageSource.getMessage("success.send.code.email", null, Locale.getDefault()));
    }

    @Override
    @Transactional
    public ResponseEntity<String> setPassword(UserPasswordResetDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.user.notfound.username", new Object[] { dto.getEmail() },
                                Locale.getDefault())));

        if (isValidVerificationCode(user, dto.getCode())) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setStatus("ACTIVE");
            clearVerificationCode(user);
            userRepository.saveAndFlush(user);
            return ResponseEntity.ok(messageSource.getMessage("success.changed.password", null, Locale.getDefault())) ;
        } else {
            return ResponseEntity.status(400).body(messageSource.getMessage("error.password.code", null, Locale.getDefault()));
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<String> sendVerificationCode(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.user.notfound.username", new Object[] { email }, Locale.getDefault())));

        try {
            generateAndSendVerificationCode(user);
            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            throw new EmailSendException(
                    messageSource.getMessage("error.send.code.email", null, Locale.getDefault()), e);
        }
        return ResponseEntity.ok(messageSource.getMessage("success.send.code.email", null, Locale.getDefault()));
    }

    private void generateAndSendVerificationCode(User model) throws MessagingException {
        String verificationCode = generateVerificationCode();
        model.setVerificationCode(verificationCode);
        model.setVerificationExpiry(LocalDateTime.now().plusMinutes(15));
        Context context = new Context();
        context.setVariable("code", verificationCode);
        context.setVariable("name", model.getName());        

        emailService.sendTemplateEmail(model.getEmail(), "Password definition", context, "userPasswordUpdate");
    }

    private String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(999999));
    }

    private boolean isValidVerificationCode(User model, String verificationCode) {
        return model.getVerificationCode().equals(verificationCode)
                && model.getVerificationExpiry().isAfter(LocalDateTime.now());
    }

    private void clearVerificationCode(User model) {
        model.setVerificationCode(null);
        model.setVerificationExpiry(null);
    }
}

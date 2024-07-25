package com.dev.simper.service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.dev.simper.dto.user.UserDto;
import com.dev.simper.dto.user.UserPasswordResetDto;
import com.dev.simper.dto.user.UserRegisterDto;
import com.dev.simper.exception.EmailSendException;
import com.dev.simper.exception.ResourceNotFoundException;
import com.dev.simper.model.User;
import com.dev.simper.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@Service
public class UserAccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Transactional
    public ResponseEntity<String> register(UserRegisterDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setStatus("PENDING");
        try {
            generateAndSendVerificationCode(user);
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailSendException(
                    messageSource.getMessage("code.email.send.error", null, Locale.getDefault()), e);
        }
        return ResponseEntity.ok(messageSource.getMessage("code.email.send.success", null, Locale.getDefault()));
    }

    @Transactional
    public ResponseEntity<String> setPassword(UserPasswordResetDto userPasswordResetDto) {
        User user = userRepository.findByEmail(userPasswordResetDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("user.notfound", new Object[] { userPasswordResetDto.getEmail() },
                                Locale.getDefault())));

        if (isValidVerificationCode(user, userPasswordResetDto.getCode())) {
            user.setPassword(passwordEncoder.encode(userPasswordResetDto.getPassword()));
            //user.setPassword("alterar a senha");
            user.setStatus("ACTIVE");
            clearVerificationCode(user);
            userRepository.save(user);
            return ResponseEntity.ok(messageSource.getMessage("password.success", null, Locale.getDefault())) ;
        } else {
            return ResponseEntity.status(400).body(messageSource.getMessage("password.error.code", null, Locale.getDefault()));
        }
    }
    

    @Transactional
    public ResponseEntity<String> sendVerificationCode(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("user.notfound", new Object[] { email }, Locale.getDefault())));

        try {
            generateAndSendVerificationCode(user);
            userRepository.save(user);
        } catch (Exception e) {
            throw new EmailSendException(
                    messageSource.getMessage("code.email.send.error", null, Locale.getDefault()), e);
        }
        return ResponseEntity.ok(messageSource.getMessage("code.email.send.success", null, Locale.getDefault()));
    }

    private void generateAndSendVerificationCode(User user) throws MessagingException {
        String verificationCode = generateVerificationCode();
        user.setVerificationCode(verificationCode);
        user.setVerificationExpiry(LocalDateTime.now().plusMinutes(15));
        Context context = new Context();
        context.setVariable("code", verificationCode);
        context.setVariable("name", user.getName());        

        emailService.sendTemplateEmail(user.getEmail(), "Definição de senha", context, "userPasswordUpdate");
    }

    private String generateVerificationCode() {
        return UUID.randomUUID().toString();
    }

    private boolean isValidVerificationCode(User user, String verificationCode) {
        return user.getVerificationCode().equals(verificationCode)
                && user.getVerificationExpiry().isAfter(LocalDateTime.now());
    }

    private void clearVerificationCode(User user) {
        user.setVerificationCode(null);
        user.setVerificationExpiry(null);
    }
}

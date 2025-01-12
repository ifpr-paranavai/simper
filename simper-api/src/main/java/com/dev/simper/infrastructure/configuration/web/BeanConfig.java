package com.dev.simper.infrastructure.configuration.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.TemplateEngine;

import com.dev.simper.infrastructure.configuration.database.repository.InstitutionRepository;
import com.dev.simper.infrastructure.configuration.database.repository.RoleRepository;
import com.dev.simper.infrastructure.configuration.database.repository.UserRepository;
import com.dev.simper.infrastructure.configuration.security.JwtTokenUtil;
import com.dev.simper.infrastructure.institution.gateway.InstitutionDatabaseGateway;
import com.dev.simper.infrastructure.role.gateway.RoleDatabaseGateway;
import com.dev.simper.infrastructure.user.gateway.UserDatabaseGateway;
import com.dev.simper.usecase.auth.implementation.AuthUseCase;
import com.dev.simper.usecase.email.contract.ISendTemplateEmailUseCase;
import com.dev.simper.usecase.email.implementation.SendTemplateEmailUseCase;
import com.dev.simper.usecase.institution.implementation.GetInstitutionUseCase;
import com.dev.simper.usecase.institution.implementation.ListInstitutionUseCase;
import com.dev.simper.usecase.institution.implementation.SaveInstitutionUseCase;
import com.dev.simper.usecase.institution.implementation.UpdateInstitutionUseCase;
import com.dev.simper.usecase.role.implementation.GetRoleUseCase;
import com.dev.simper.usecase.role.implementation.ListRoleUseCase;
import com.dev.simper.usecase.role.implementation.SaveRoleUseCase;
import com.dev.simper.usecase.role.implementation.UpdateRoleUseCase;
import com.dev.simper.usecase.user.contract.ILoadUserDetailsUseCase;
import com.dev.simper.usecase.user.contract.IRegisterUserAccountUseCase;
import com.dev.simper.usecase.user.implementation.ChangePasswordUserAccountUseCase;
import com.dev.simper.usecase.user.implementation.DeleteUserUseCase;
import com.dev.simper.usecase.user.implementation.GetUserUseCase;
import com.dev.simper.usecase.user.implementation.ListUserUseCase;
import com.dev.simper.usecase.user.implementation.LoadUserDetailsUseCase;
import com.dev.simper.usecase.user.implementation.RegisterUserAccountUseCase;
import com.dev.simper.usecase.user.implementation.SaveUserUseCase;
import com.dev.simper.usecase.user.implementation.SetPasswordUserAccountUseCase;
import com.dev.simper.usecase.user.implementation.UpdateUserUseCase;

@Configuration
public class BeanConfig {

    @Bean
    AuthUseCase authUseCase(
        AuthenticationManager authenticationManager,
        ILoadUserDetailsUseCase iLoadUserDetailsUseCase,
        JwtTokenUtil jwtTokenUtil,
        MessageSource messageSource
    ) {
        return new AuthUseCase(authenticationManager, iLoadUserDetailsUseCase, jwtTokenUtil, messageSource);
    }

    @Bean
    SendTemplateEmailUseCase emailUseCase(
        JavaMailSender javaMailSender, 
        TemplateEngine templateEngine
    ) {
        return new SendTemplateEmailUseCase(javaMailSender, templateEngine);
    }

    @Bean
    SaveInstitutionUseCase saveInstitutionUseCase(
        InstitutionRepository institutionRepository,
        IRegisterUserAccountUseCase iRegisterUserAccountUseCase
    ) {
        return new SaveInstitutionUseCase(
            new InstitutionDatabaseGateway(institutionRepository),
            iRegisterUserAccountUseCase
        );
    }

    @Bean
    UpdateInstitutionUseCase updateInstitutionUseCase(
        InstitutionRepository institutionRepository
    ) {
        return new UpdateInstitutionUseCase(
            new InstitutionDatabaseGateway(institutionRepository)
        );
    }

    @Bean
    GetInstitutionUseCase getInstitutionUseCase(
        InstitutionRepository institutionRepository,
        MessageSource messageSource
    ) {
        return new GetInstitutionUseCase(
            new InstitutionDatabaseGateway(institutionRepository), 
            messageSource
        );
    }

    @Bean
    ListInstitutionUseCase listInstitutionUseCase(
        InstitutionRepository institutionRepository
    ) {
        return new ListInstitutionUseCase(
            new InstitutionDatabaseGateway(institutionRepository)
        ); 
    }

    @Bean
    SaveRoleUseCase saveRoleUseCase(
        RoleRepository roleRepository
    ) {
        return new SaveRoleUseCase(
            new RoleDatabaseGateway(roleRepository)
        );
    }

    @Bean
    UpdateRoleUseCase updateRoleUseCase(
        RoleRepository roleRepository
    ) {
        return new UpdateRoleUseCase(
            new RoleDatabaseGateway(roleRepository)
        );
    }

    @Bean
    GetRoleUseCase getRoleUseCase(
        RoleRepository roleRepository,
        MessageSource messageSource
    ) {
        return new GetRoleUseCase(
            new RoleDatabaseGateway(roleRepository), 
            messageSource
        );
    }

    @Bean
    ListRoleUseCase listRoleUseCase(
        RoleRepository roleRepository
    ) {
        return new ListRoleUseCase(
            new RoleDatabaseGateway(roleRepository)
        ); 
    }

    @Bean
    RegisterUserAccountUseCase registerUserAccountUseCase(
        UserRepository userRepository,
        ISendTemplateEmailUseCase iSendTemplateEmailUseCase,
        MessageSource messageSource
    ) {
        return new RegisterUserAccountUseCase(
            new UserDatabaseGateway(userRepository),
            iSendTemplateEmailUseCase,
            messageSource
        );
    }

    @Bean
    ChangePasswordUserAccountUseCase changePasswordUserAccountUseCase(
        UserRepository userRepository,
        ISendTemplateEmailUseCase iSendTemplateEmailUseCase,
        MessageSource messageSource
    ) {
        return new ChangePasswordUserAccountUseCase(
            new UserDatabaseGateway(userRepository), 
            iSendTemplateEmailUseCase, 
            messageSource
        );
    }

    @Bean
    SetPasswordUserAccountUseCase setPasswordUserAccountUseCase(
        UserRepository userRepository,
        MessageSource messageSource,
        PasswordEncoder passwordEncoder
    ) {
        return new SetPasswordUserAccountUseCase(
            new UserDatabaseGateway(userRepository),
            messageSource,
            passwordEncoder
        );
    }

    @Bean
    LoadUserDetailsUseCase loadUserDetailsUseCase(
        UserRepository userRepository,
        MessageSource messageSource
    ) {
        return new LoadUserDetailsUseCase(
            new UserDatabaseGateway(userRepository),
            messageSource
        );
    }

    @Bean
    SaveUserUseCase saveUserUseCase(
        UserRepository userRepository
    ) {
        return new SaveUserUseCase(
            new UserDatabaseGateway(userRepository)
        );
    }

    @Bean
    UpdateUserUseCase updateUserUseCase(
        UserRepository userRepository
    ) {
        return new UpdateUserUseCase(
            new UserDatabaseGateway(userRepository)
        );
    }

    @Bean
    DeleteUserUseCase deleteUserUseCase(
        UserRepository userRepository
    ) {
        return new DeleteUserUseCase(
            new UserDatabaseGateway(userRepository)
        );
    }

    @Bean
    GetUserUseCase getUserUseCase(
        UserRepository userRepository,
        MessageSource messageSource
    ) {
        return new GetUserUseCase(
            new UserDatabaseGateway(userRepository),
            messageSource
        );
    }

    @Bean
    ListUserUseCase listUserUseCase(
        UserRepository userRepository
    ) {
        return new ListUserUseCase(
            new UserDatabaseGateway(userRepository)
        );
    }
}
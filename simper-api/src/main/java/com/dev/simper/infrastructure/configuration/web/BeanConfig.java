package com.dev.simper.infrastructure.configuration.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.TemplateEngine;

import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.infrastructure.configuration.database.repository.InstitutionRepository;
import com.dev.simper.infrastructure.configuration.database.repository.RoleRepository;
import com.dev.simper.infrastructure.configuration.database.repository.UserRepository;
import com.dev.simper.infrastructure.configuration.security.JwtTokenUtil;
import com.dev.simper.infrastructure.institution.gateway.InstitutionDatabaseGateway;
import com.dev.simper.infrastructure.role.gateway.RoleDatabaseGateway;
import com.dev.simper.infrastructure.user.gateway.UserDatabaseGateway;
import com.dev.simper.usecase.auth.implementation.AuthUseCase;
import com.dev.simper.usecase.email.contract.IEmailUseCase;
import com.dev.simper.usecase.email.implementation.EmailUseCase;
import com.dev.simper.usecase.institution.implementation.GetInstitutionUseCase;
import com.dev.simper.usecase.institution.implementation.ListInstitutionUseCase;
import com.dev.simper.usecase.institution.implementation.SaveInstitutionUseCase;
import com.dev.simper.usecase.institution.implementation.UpdateInstitutionUseCase;
import com.dev.simper.usecase.role.implementation.GetRoleUseCase;
import com.dev.simper.usecase.role.implementation.ListRoleUseCase;
import com.dev.simper.usecase.role.implementation.SaveRoleUseCase;
import com.dev.simper.usecase.role.implementation.UpdateRoleUseCase;
import com.dev.simper.usecase.user.contract.IUserAccountUseCase;
import com.dev.simper.usecase.user.implementation.UserAccountUseCase;
import com.dev.simper.usecase.user.implementation.UserDetailsUseCase;
import com.dev.simper.usecase.user.implementation.UserUseCase;

@Configuration
public class BeanConfig {

    @Bean
    AuthUseCase authUseCase(
        AuthenticationManager authenticationManager,
        UserDetailsUseCase userDetailsServiceImpl,
        JwtTokenUtil jwtTokenUtil,
        MessageSource messageSource
    ) {
        return new AuthUseCase(authenticationManager, userDetailsServiceImpl, jwtTokenUtil, messageSource);
    }

    @Bean
    EmailUseCase emailUseCase(
        JavaMailSender javaMailSender, 
        TemplateEngine templateEngine
    ) {
        return new EmailUseCase(javaMailSender, templateEngine);
    }

    @Bean
    SaveInstitutionUseCase saveInstitutionUseCase(
        InstitutionRepository institutionRepository,
        IUserAccountUseCase iUserAccountUseCase
    ) {
        return new SaveInstitutionUseCase(
            new InstitutionDatabaseGateway(institutionRepository),
            iUserAccountUseCase
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
    UserAccountUseCase userAccountUseCase(
        UserRepository userRepository,
        IEmailUseCase iEmailUseCase,
        MessageSource messageSource,
        PasswordEncoder passwordEncoder
    ) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new UserAccountUseCase(userGateway, iEmailUseCase, messageSource, passwordEncoder);
    }

    @Bean
    UserDetailsUseCase userDetailsUseCase(
        UserRepository userRepository,
        MessageSource messageSource
    ) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new UserDetailsUseCase(userGateway, messageSource);
    }

    @Bean
    UserUseCase UserUseCase(
        UserRepository userRepository, 
        MessageSource messageSource
    ) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new UserUseCase(userGateway, messageSource);
    }
}
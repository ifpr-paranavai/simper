package com.dev.simper.usecase.user.implementation;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.usecase.user.contract.IUserDetailsUseCase;

public class UserDetailsUseCase implements IUserDetailsUseCase {

    private final UserGateway userGateway;
    private final MessageSource messageSource;

    public UserDetailsUseCase(UserGateway userGateway, MessageSource messageSource) {
        this.userGateway = userGateway;
        this.messageSource = messageSource;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userGateway.findByEmail(username)
                .orElseThrow(() -> 
                    new UsernameNotFoundException(messageSource.getMessage("error.user.notfound.username", new Object[] { username }, Locale.getDefault()))
                );
    }
}

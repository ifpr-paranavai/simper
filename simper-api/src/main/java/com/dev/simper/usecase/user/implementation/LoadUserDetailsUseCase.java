package com.dev.simper.usecase.user.implementation;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dev.simper.entity.user.gateway.UserGateway;
import com.dev.simper.usecase.user.contract.ILoadUserDetailsUseCase;

public class LoadUserDetailsUseCase implements ILoadUserDetailsUseCase {

    private final UserGateway userGateway;
    private final MessageSource messageSource;

    public LoadUserDetailsUseCase(
        UserGateway userGateway, 
        MessageSource messageSource
    ) {
        this.userGateway = userGateway;
        this.messageSource = messageSource;
    }

    @Override
    public UserDetails execute(String username) throws UsernameNotFoundException {
        return userGateway.findByEmail(username)
                .orElseThrow(() -> 
                    new UsernameNotFoundException(messageSource.getMessage("error.user.notfound.username", new Object[] { username }, Locale.getDefault()))
                );
    }
}

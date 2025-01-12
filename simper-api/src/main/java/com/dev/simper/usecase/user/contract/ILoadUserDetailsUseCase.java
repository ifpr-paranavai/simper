package com.dev.simper.usecase.user.contract;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ILoadUserDetailsUseCase {
    UserDetails execute(String username) throws UsernameNotFoundException;
}

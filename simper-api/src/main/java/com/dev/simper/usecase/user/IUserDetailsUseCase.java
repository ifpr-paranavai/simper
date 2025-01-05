package com.dev.simper.usecase.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDetailsUseCase {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

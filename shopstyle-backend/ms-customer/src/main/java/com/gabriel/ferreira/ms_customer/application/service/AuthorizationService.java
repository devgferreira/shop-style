package com.gabriel.ferreira.ms_customer.application.service;

import com.gabriel.ferreira.ms_customer.domain.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthorizationService {
    private final IUserRepository _userRepository;

    public AuthorizationService(IUserRepository userRepository) {
        _userRepository = userRepository;
    }


}

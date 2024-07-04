package com.gabriel.ferreira.ms_customer.application.service;

import com.gabriel.ferreira.ms_customer.application.interfaces.IUserService;
import com.gabriel.ferreira.ms_customer.domain.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    private final IUserService _userService;

    public AuthorizationService(IUserService userService) {
        _userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return _userService.buscarUserPorEmail(username);
    }
}

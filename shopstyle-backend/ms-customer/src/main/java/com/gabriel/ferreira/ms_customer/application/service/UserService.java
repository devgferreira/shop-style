package com.gabriel.ferreira.ms_customer.application.service;

import com.gabriel.ferreira.ms_customer.application.interfaces.IUserService;
import com.gabriel.ferreira.ms_customer.domain.model.user.User;
import com.gabriel.ferreira.ms_customer.domain.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository _userRepository;

    public UserService(IUserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public User criarUser(User user) {
        return _userRepository.save(user);
    }

    @Override
    public UserDetails buscarUserPorEmail(String email) {

        return _userRepository.findByEmail(email);
    }
}

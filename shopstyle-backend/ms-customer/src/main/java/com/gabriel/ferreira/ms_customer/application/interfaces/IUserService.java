package com.gabriel.ferreira.ms_customer.application.interfaces;

import com.gabriel.ferreira.ms_customer.domain.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    User criarUser(User user);
    UserDetails buscarUserPorEmail(String email);
}

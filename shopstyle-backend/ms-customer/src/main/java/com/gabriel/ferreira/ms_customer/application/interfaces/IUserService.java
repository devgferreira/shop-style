package com.gabriel.ferreira.ms_customer.application.interfaces;

import com.gabriel.ferreira.ms_customer.domain.model.user.User;

public interface IUserService {
    User criarUser(User user);
    User buscarUserPorEmail(String email);
}

package com.gabriel.ferreira.ms_customer.domain.model.user.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Login {
   private String email;
    private String password;
}

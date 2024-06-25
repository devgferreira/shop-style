package com.gabriel.ferreira.ms_customer.domain.model.customer.request;

import com.gabriel.ferreira.ms_customer.domain.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomerRequest {
    private String cpf;
    private String firstName;
    private String lastName;
    private Sex sex;
    private Date birthdate;
    private String email;
    private String password;
    private Boolean active;
}

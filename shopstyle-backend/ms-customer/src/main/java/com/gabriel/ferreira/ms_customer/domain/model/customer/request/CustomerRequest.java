package com.gabriel.ferreira.ms_customer.domain.model.customer.request;

import com.gabriel.ferreira.ms_customer.domain.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private Sex sex;
    private String cpf;
    private Date birthdate;
    private String email;
    private String password;
    private Boolean active;
}

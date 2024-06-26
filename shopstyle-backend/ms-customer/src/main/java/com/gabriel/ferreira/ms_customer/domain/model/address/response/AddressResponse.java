package com.gabriel.ferreira.ms_customer.domain.model.address.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResponse {
    private String state;
    private String city;
    private String street;
    private Integer number;
    private String cep;
    private String complement;
}

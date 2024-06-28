package com.gabriel.ferreira.ms_customer.domain.model.address.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private Integer id;
    private String state;
    private String city;
    private String district;
    private String street;
    private Integer number;
    private String cep;
    private String complement;
}

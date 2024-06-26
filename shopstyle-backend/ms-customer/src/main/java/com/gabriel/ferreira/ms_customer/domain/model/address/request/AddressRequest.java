package com.gabriel.ferreira.ms_customer.domain.model.address.request;

import com.gabriel.ferreira.ms_customer.domain.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressRequest {
    private String state;
    private String city;
    private String district;
    private String street;
    private Integer number;
    private String cep;
    private String complement;
    private Integer customerId;
}

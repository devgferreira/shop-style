package com.gabriel.ferreira.ms_customer.application.interfaces;

import com.gabriel.ferreira.ms_customer.domain.model.address.request.AddressRequest;
import com.gabriel.ferreira.ms_customer.domain.model.address.response.AddressResponse;

public interface IAddressService {
    AddressResponse criarAddress(AddressRequest addressRequest);
    AddressResponse atualizarAddressPorCustomerId(AddressRequest addressRequest, Integer customerId);
}

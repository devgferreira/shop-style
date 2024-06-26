package com.gabriel.ferreira.ms_customer.application.interfaces;

import com.gabriel.ferreira.ms_customer.domain.model.address.request.AddressRequest;
import com.gabriel.ferreira.ms_customer.domain.model.address.response.AddressResponse;

public interface IAddressService {
    AddressResponse criarAddress(AddressRequest addressRequest);
    AddressResponse buscarAddressPorCustomerId(Integer customerId);
    AddressResponse atualizarAddressPorCustomerId(AddressRequest addressRequest, Integer customerId);
    void deleterAddressPorCustomerId(Integer customerId);
}

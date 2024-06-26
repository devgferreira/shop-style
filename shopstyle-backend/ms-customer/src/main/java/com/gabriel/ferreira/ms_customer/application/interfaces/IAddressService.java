package com.gabriel.ferreira.ms_customer.application.interfaces;

import com.gabriel.ferreira.ms_customer.domain.model.address.request.AddressRequest;
import com.gabriel.ferreira.ms_customer.domain.model.address.response.AddressResponse;

import java.util.List;

public interface IAddressService {
    AddressResponse criarAddress(AddressRequest addressRequest);
    List<AddressResponse> buscarAddressesPorCustomerId(Integer customerId);
    AddressResponse atualizarAddressPorId(AddressRequest addressRequest, Integer addressId);
    void deleterAddressPorCustomerId(Integer customerId);
}

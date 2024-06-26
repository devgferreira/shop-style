package com.gabriel.ferreira.ms_customer.application.service;

import com.gabriel.ferreira.ms_customer.application.interfaces.IAddressService;
import com.gabriel.ferreira.ms_customer.domain.model.address.Address;
import com.gabriel.ferreira.ms_customer.domain.model.address.request.AddressRequest;
import com.gabriel.ferreira.ms_customer.domain.model.address.response.AddressResponse;
import com.gabriel.ferreira.ms_customer.domain.repository.IAddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {

    private final IAddressRepository _addressRepository;
    private final ModelMapper _modelMapper;

    public AddressService(IAddressRepository addressRepository, ModelMapper modelMapper) {
        _addressRepository = addressRepository;
        _modelMapper = modelMapper;
    }

    @Override
    public AddressResponse criarAddress(AddressRequest addressRequest) {
        validarAddressAtributos(addressRequest);
        validarStates(addressRequest);
        return null;
    }




    @Override
    public AddressResponse atualizarAddressPorCustomerId(AddressRequest addressRequest, Integer customerId) {
        return null;
    }

    @Override
    public void deleterAddressPorCustomerId(Integer customerId) {

    }

    private static void validarAddressAtributos(AddressRequest addressRequest) {
        boolean addressValido = addressRequest.getCep().isEmpty()  || addressRequest.getNumber() == null ||
                addressRequest.getCity().isEmpty() || addressRequest.getState().isEmpty() || addressRequest.getDistrict().isEmpty() ||
                addressRequest.getStreet().isEmpty() || addressRequest.getCustomerId() == null;
        if (addressValido){
            throw new RuntimeException("Address inválido");
        }
    }
    private static void validarStates(AddressRequest addressRequest) {
        String[] states = {
                "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia",
                "Ceará", "Distrito Federal", "Espírito Santo", "Goiás",
                "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais",
                "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro",
                "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima",
                "Santa Catarina", "São Paulo", "Sergipe", "Tocantins"
        };
        for (String state : states) {
            if (!state.equals(addressRequest.getState())) {
                throw new RuntimeException("State inválido");
            }
        }
    }
}

package com.gabriel.ferreira.ms_customer.application.service;

import com.gabriel.ferreira.ms_customer.application.interfaces.IAddressService;
import com.gabriel.ferreira.ms_customer.domain.model.address.Address;
import com.gabriel.ferreira.ms_customer.domain.model.address.request.AddressRequest;
import com.gabriel.ferreira.ms_customer.domain.model.address.response.AddressResponse;
import com.gabriel.ferreira.ms_customer.domain.repository.IAddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        validarStates(addressRequest.getState());
        Address address = _modelMapper.map(addressRequest, Address.class);
        return _modelMapper.map(_addressRepository.save(address), AddressResponse.class);
    }

    @Override
    public List<AddressResponse> buscarAddressesPorCustomerId(Integer customerId) {
        List<Address> addresses = _addressRepository.findAllByCustomerId(customerId);
        return addresses.stream().map(address -> _modelMapper.map(address, AddressResponse.class)).collect(Collectors.toList());
    }


    @Override
    public AddressResponse atualizarAddressPorId(AddressRequest addressRequest, Integer addressId) {
        Address address = _addressRepository.findById(addressId).get();
        validarStates(addressRequest.getState());

        Optional.ofNullable(addressRequest.getState()).filter(state -> !state.isEmpty()).ifPresent(address::setState);
        Optional.ofNullable(addressRequest.getCity()).filter(city -> !city.isEmpty()).ifPresent(address::setCity);
        Optional.ofNullable(addressRequest.getDistrict()).filter(district-> !district.isEmpty()).ifPresent(address::setDistrict);
        Optional.ofNullable(addressRequest.getStreet()).filter(street -> !street.isEmpty()).ifPresent(address::setStreet);
        Optional.ofNullable(addressRequest.getNumber()).ifPresent(address::setNumber);
        Optional.ofNullable(addressRequest.getCep()).filter(cep -> !cep.isEmpty()).ifPresent(address::setCep);
        _addressRepository.save(address);
        return _modelMapper.map(address,AddressResponse.class);
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
    private static void validarStates(String addressState) {
        String[] states = {
                "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia",
                "Ceará", "Distrito Federal", "Espírito Santo", "Goiás",
                "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais",
                "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro",
                "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima",
                "Santa Catarina", "São Paulo", "Sergipe", "Tocantins"
        };
        for (String state : states) {
            if (!state.equals(addressState)) {
                throw new RuntimeException("State inválido");
            }
        }
    }
}

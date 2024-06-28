package com.gabriel.ferreira.ms_customer.application.service;

import com.gabriel.ferreira.ms_customer.application.interfaces.IAddressService;
import com.gabriel.ferreira.ms_customer.domain.enums.ErrorCodes;
import com.gabriel.ferreira.ms_customer.domain.model.address.Address;
import com.gabriel.ferreira.ms_customer.domain.model.address.request.AddressRequest;
import com.gabriel.ferreira.ms_customer.domain.model.address.response.AddressResponse;
import com.gabriel.ferreira.ms_customer.domain.model.customer.Customer;
import com.gabriel.ferreira.ms_customer.domain.repository.IAddressRepository;
import com.gabriel.ferreira.ms_customer.infra.exception.ExceptionResponse;
import com.gabriel.ferreira.ms_customer.infra.exception.address.AddressInvalidoException;
import com.gabriel.ferreira.ms_customer.infra.exception.address.AddressNaoEncontradoException;
import com.gabriel.ferreira.ms_customer.infra.exception.address.AddressStateException;
import com.gabriel.ferreira.ms_customer.infra.exception.constant.ErrorConstant;
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

        Customer customer = new Customer();
        customer.setId(addressRequest.getCustomerId());

        Address address = new Address();
        address.setCity(addressRequest.getCity());
        address.setCep(addressRequest.getCep());
        address.setDistrict(addressRequest.getDistrict());
        address.setState(addressRequest.getState());
        address.setComplement(addressRequest.getComplement());
        address.setStreet(addressRequest.getStreet());
        address.setNumber(addressRequest.getNumber());
        address.setCustomer(customer);

        address.setCustomer(customer);
        _addressRepository.save(address);


        return _modelMapper.map(address, AddressResponse.class);
    }

    @Override
    public List<AddressResponse> buscarAddressesPorCustomerId(Integer customerId) {
        List<Address> addresses = _addressRepository.findAllByCustomerId(customerId);
        return addresses.stream().map(address -> _modelMapper.map(address, AddressResponse.class)).collect(Collectors.toList());
    }


    @Override
    public AddressResponse atualizarAddressPorId(AddressRequest addressRequest, Integer addressId) {
        Address address = _addressRepository.findById(addressId).orElseThrow(() ->
                new AddressNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.ADDRESS_NAO_ENCONTRADA, ErrorConstant.ADDRESS_NAO_ENCONTRADA)
                ));
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
    public void deleterAddressPorId(Integer addressId) {
        Address address = _addressRepository.findById(addressId).orElseThrow(() ->
                new AddressNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.ADDRESS_NAO_ENCONTRADA, ErrorConstant.ADDRESS_NAO_ENCONTRADA)
                ));
        _addressRepository.deleteById(address.getId());
    }

    private static void validarAddressAtributos(AddressRequest addressRequest) {
        boolean addressValido = addressRequest.getCep().isEmpty()  || addressRequest.getNumber() == null ||
                addressRequest.getCity().isEmpty() || addressRequest.getState().isEmpty() || addressRequest.getDistrict().isEmpty() ||
                addressRequest.getStreet().isEmpty() || addressRequest.getCustomerId() == null;
        if (addressValido){
            throw new AddressInvalidoException(
                    new ExceptionResponse(ErrorCodes.ADDRESS_INVALIDO, ErrorConstant.ADDRESS_INVALIDO)
            );
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
        boolean found = false;
        for (String state : states) {
            if (state.equals(addressState)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new AddressStateException(
                    new ExceptionResponse(
                            ErrorCodes.ADDRESS_STATE_INVALIDO, ErrorConstant.ADDRESS_STATE_INVALIDO
                    )
            );
        }
    }
}

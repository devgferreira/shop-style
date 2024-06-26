package com.gabriel.ferreira.ms_customer.application.service;

import com.gabriel.ferreira.ms_customer.application.interfaces.IAddressService;
import com.gabriel.ferreira.ms_customer.application.interfaces.ICustomerService;
import com.gabriel.ferreira.ms_customer.domain.enums.Sex;
import com.gabriel.ferreira.ms_customer.domain.model.address.response.AddressResponse;
import com.gabriel.ferreira.ms_customer.domain.model.customer.Customer;
import com.gabriel.ferreira.ms_customer.domain.model.customer.request.CustomerRequest;
import com.gabriel.ferreira.ms_customer.domain.model.customer.response.CustomerResponse;
import com.gabriel.ferreira.ms_customer.domain.repository.ICustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    private final ICustomerRepository _customerRepository;
    private final IAddressService _addressService;
    private final ModelMapper _modelMapper;


    public CustomerService(ICustomerRepository customerRepository, IAddressService addressService, ModelMapper modelMapper) {
        _customerRepository = customerRepository;
        _addressService = addressService;
        _modelMapper = modelMapper;
    }

    @Override
    public CustomerResponse criarCustomer(CustomerRequest customerRequest) {
        validarAtributosCustomer(customerRequest);
        validarSeEmailJaExiste(customerRequest.getEmail());
        validarSeCpfJaExiste(customerRequest.getCpf());
        Customer customer = _modelMapper.map(customerRequest, Customer.class);
        return _modelMapper.map(_customerRepository.save(customer), CustomerResponse.class);
    }

    @Override
    public CustomerResponse buscarCustomerPorId(Integer customerId) {
        Customer customer = _customerRepository.findById(customerId).orElseThrow(
                () -> new RuntimeException("Customer não encontrado"));

        List<AddressResponse> addressResponses = _addressService.buscarAddressesPorCustomerId(customer.getId());

        CustomerResponse customerResponse = _modelMapper.map(customer, CustomerResponse.class);
        customerResponse.setAddresses(addressResponses);

        return customerResponse;
    }

    @Override
    public CustomerResponse atualizarCustomer(CustomerRequest customerRequest, Integer customerId) {
        return null;
    }

    private static void validarAtributosCustomer(CustomerRequest customerRequest){
        boolean customerCamposVazios = customerRequest.getFirstName().isEmpty() || customerRequest.getLastName().isEmpty()
                || customerRequest.getCpf().isEmpty() || customerRequest.getEmail().isEmpty()
                || customerRequest.getPassword().isEmpty() || customerRequest.getBirthdate() == null
                || customerRequest.getSex() == null || customerRequest.getActive() == null;

        if (customerCamposVazios){
            throw new RuntimeException("Campos Vazios");
        }
        if(customerRequest.getFirstName().length() < 3){
            throw new RuntimeException("< 3 firstName");
        }
        if(customerRequest.getLastName().length() < 3){
            throw new RuntimeException("< 3 lastName");
        }
        boolean customerSexValido = customerRequest.getSex().equals(Sex.MASCULINO) || customerRequest.getSex().equals(Sex.FEMININO) ;
        if (!customerSexValido){
            throw new RuntimeException("Sexo incorreto");
        }
        if(customerRequest.getPassword().length() < 6){
            throw new RuntimeException("Senha < 6");
        }
    }
    private void validarSeEmailJaExiste(String email) {
        _customerRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email ja existe")
        );
    }
    private void validarSeCpfJaExiste(String cpf) {
        _customerRepository.findByCpf(cpf).orElseThrow(
                () -> new RuntimeException("Email ja existe")
        );
    }
}

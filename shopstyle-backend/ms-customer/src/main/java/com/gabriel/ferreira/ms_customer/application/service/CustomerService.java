package com.gabriel.ferreira.ms_customer.application.service;

import com.gabriel.ferreira.ms_customer.application.interfaces.IAddressService;
import com.gabriel.ferreira.ms_customer.application.interfaces.ICustomerService;
import com.gabriel.ferreira.ms_customer.application.interfaces.IUserService;
import com.gabriel.ferreira.ms_customer.domain.enums.ErrorCodes;
import com.gabriel.ferreira.ms_customer.domain.enums.Sex;
import com.gabriel.ferreira.ms_customer.domain.enums.UserRole;
import com.gabriel.ferreira.ms_customer.domain.model.address.response.AddressResponse;
import com.gabriel.ferreira.ms_customer.domain.model.customer.Customer;
import com.gabriel.ferreira.ms_customer.domain.model.customer.request.CustomerRequest;
import com.gabriel.ferreira.ms_customer.domain.model.customer.response.CustomerResponse;
import com.gabriel.ferreira.ms_customer.domain.model.user.User;
import com.gabriel.ferreira.ms_customer.domain.repository.ICustomerRepository;
import com.gabriel.ferreira.ms_customer.infra.exception.ExceptionResponse;
import com.gabriel.ferreira.ms_customer.infra.exception.constant.ErrorConstant;
import com.gabriel.ferreira.ms_customer.infra.exception.customer.*;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CustomerService implements ICustomerService {
    private final ICustomerRepository _customerRepository;
    private final IAddressService _addressService;
    private final ModelMapper _modelMapper;
    private final IUserService _userService;


    public CustomerService(ICustomerRepository customerRepository, IAddressService addressService, ModelMapper modelMapper, IUserService userService) {
        _customerRepository = customerRepository;
        _addressService = addressService;
        _modelMapper = modelMapper;
        _userService = userService;
    }

    @Override
    public CustomerResponse criarCustomer(CustomerRequest customerRequest) {
        validarAtributosCustomer(customerRequest);
        validarSeEmailJaExiste(customerRequest.getEmail());
        validarSeCpfJaExiste(customerRequest.getCpf());

        Customer customer = _modelMapper.map(customerRequest, Customer.class);

        String senhaCriptografada = new BCryptPasswordEncoder().encode(customer.getPassword());
        customer.setPassword(senhaCriptografada);

        User user = new User(customer.getEmail(), customer.getPassword(), UserRole.USER);

        _userService.criarUser(user);
        return _modelMapper.map(_customerRepository.save(customer), CustomerResponse.class);
    }

    @Override
    public CustomerResponse buscarCustomerPorId(Integer customerId) {
        Customer customer = _customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.CUSTOMER_NAO_ENCONTRADO, ErrorConstant.CUSTOMER_NAO_ENCONTRADO))
                );

        List<AddressResponse> addressResponses = _addressService.buscarAddressesPorCustomerId(customer.getId());

        CustomerResponse customerResponse = _modelMapper.map(customer, CustomerResponse.class);
        customerResponse.setAddresses(addressResponses);

        return customerResponse;
    }

    @Override
    public CustomerResponse atualizarCustomer(CustomerRequest customerRequest, Integer customerId) {
        validarAtributosCustomer(customerRequest);
        Customer customer = _customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.CUSTOMER_NAO_ENCONTRADO, ErrorConstant.CUSTOMER_NAO_ENCONTRADO))
        );
        Optional.ofNullable(customerRequest.getFirstName()).filter(firstName -> !firstName.isEmpty()).ifPresent(customer::setFirstName);
        Optional.ofNullable(customerRequest.getLastName()).filter(lastName -> !lastName.isEmpty()).ifPresent(customer::setLastName);
        Optional.ofNullable(customerRequest.getCpf()).filter(cpf -> !cpf.isEmpty()).ifPresent(customer::setCpf);
        Optional.ofNullable(customerRequest.getEmail()).filter(email -> !email.isEmpty()).ifPresent(customer::setEmail);
        Optional.ofNullable(customerRequest.getBirthdate()).ifPresent(customer::setBirthdate);
        Optional.ofNullable(customerRequest.getSex()).ifPresent(customer::setSex);
        Optional.ofNullable(customerRequest.getPassword()).filter(password -> !password.isEmpty()).ifPresent(customer::setPassword);
        Optional.ofNullable(customerRequest.getActive()).ifPresent(customer::setActive);

        return _modelMapper.map(customer, CustomerResponse.class);

    }

    private static void validarAtributosCustomer(CustomerRequest customerRequest){
        boolean customerCamposVazios = customerRequest.getFirstName().isEmpty() || customerRequest.getLastName().isEmpty()
                || customerRequest.getCpf().isEmpty() || customerRequest.getEmail().isEmpty()
                || customerRequest.getPassword().isEmpty() || customerRequest.getBirthdate() == null
                || customerRequest.getSex() == null || customerRequest.getActive() == null;

        if (customerCamposVazios){
            throw
                    new CustomerInvalidoException(
                            new ExceptionResponse(ErrorCodes.CUSTOMER_INVALIDO, ErrorConstant.CUSTOMER_INVALIDO));
        }
        if(customerRequest.getFirstName().length() < 3){
            throw new CustomerFirstNameInvalidoException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_FIRST_NAME_INVALIDO, ErrorConstant.CUSTOMER_FIRST_NAME_INVALIDO));
        }
        if(customerRequest.getLastName().length() < 3){
            throw new CustomerLastNameInvalidoException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_LAST_NAME_INVALIDO, ErrorConstant.CUSTOMER_LAST_NAME_INVALIDO)
            );
        }
        boolean customerSexValido = customerRequest.getSex().toString().equals("Feminino") || customerRequest.getSex().toString().equals("Masculino") ;
        if (!customerSexValido){
            throw new CustomerSexInvalidoException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_SEX_INVALIDO, ErrorConstant.CUSTOMER_SEX_INVALIDO)
            );
        }
        if(customerRequest.getPassword().length() < 6){
            throw new CustomerPasswordInvalidoException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_PASSWORD_INVALIDO, ErrorConstant.CUSTOMER_PASSWORD_INVALIDO)
            );
        }
        validarCpf(customerRequest.getCpf());
        validarEmail(customerRequest.getEmail());
    }
    private void validarSeEmailJaExiste(String email) {
        Optional<Customer> customer = _customerRepository.findByEmail(email);
        if(customer.isPresent()){
            throw new CustomerEmailJaExisteException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_EMAIL_JA_EXISTE, ErrorConstant.CUSTOMER_EMAIL_JA_EXISTE)
            );
        }
    }
    private void validarSeCpfJaExiste(String cpf) {
        Optional<Customer> customer = _customerRepository.findByCpf(cpf);
        if(customer.isPresent()){
            throw new CustomerCpfJaExisteException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_CPF_JA_EXISTE, ErrorConstant.CUSTOMER_CPF_JA_EXISTE)
            );
        }
    }
    private static void validarCpf(String cpf){
        if(cpf.length() != 14){
            throw new CustomerCpfInvalidoException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_CPF_INVALIDO, ErrorConstant.CUSTOMER_CPF_INVALIDO)
            );
        }

        CPFValidator validator = new CPFValidator();
        validator.initialize(null);
        if(!validator.isValid(cpf, null)){
            throw new CustomerCpfInvalidoException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_CPF_INVALIDO, ErrorConstant.CUSTOMER_CPF_INVALIDO)
            );
        }
    }
    private static void validarEmail(String email){
        String regex = "^[\\w+.\\-]+@[\\w+.-]+\\.[\\w]{2,}$";
        if (!Pattern.matches(regex, email)){
            throw new CustomerEmailInvalidoException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_EMAIL_INVALIDO, ErrorConstant.CUSTOMER_EMAIL_INVALIDO)
            );
        }
    }
}

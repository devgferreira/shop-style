package com.gabriel.ferreira.ms_customer.application.interfaces;

import com.gabriel.ferreira.ms_customer.domain.model.customer.request.CustomerRequest;
import com.gabriel.ferreira.ms_customer.domain.model.customer.response.CustomerResponse;

public interface ICustomerService {
    CustomerResponse criarCustomer(CustomerRequest customerRequest);
    CustomerResponse buscarCustomerPorId(Integer customerId);

}

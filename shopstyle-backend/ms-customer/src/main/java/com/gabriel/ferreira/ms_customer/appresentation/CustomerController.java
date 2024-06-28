package com.gabriel.ferreira.ms_customer.appresentation;

import com.gabriel.ferreira.ms_customer.application.interfaces.ICustomerService;
import com.gabriel.ferreira.ms_customer.domain.model.customer.request.CustomerRequest;
import com.gabriel.ferreira.ms_customer.domain.model.customer.response.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final ICustomerService _customerService;

    public CustomerController(ICustomerService customerService) {
        _customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> criarCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = _customerService.criarCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }
    @GetMapping(value = "/id/{customerId}")
    public ResponseEntity<CustomerResponse> buscarCustomerPorId(@PathVariable Integer customerId){
        CustomerResponse customerResponse = _customerService.buscarCustomerPorId(customerId);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
    @PutMapping(value = "/id/{customerId}")
    public ResponseEntity<CustomerResponse> atualizarCustomerPorId(@RequestBody CustomerRequest customerRequest, @PathVariable Integer customerId){
        CustomerResponse customerResponse = _customerService.atualizarCustomer(customerRequest, customerId);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
}

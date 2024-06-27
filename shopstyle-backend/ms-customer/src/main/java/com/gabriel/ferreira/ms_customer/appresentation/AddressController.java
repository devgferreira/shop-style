package com.gabriel.ferreira.ms_customer.appresentation;

import com.gabriel.ferreira.ms_customer.application.interfaces.IAddressService;
import com.gabriel.ferreira.ms_customer.domain.model.address.request.AddressRequest;
import com.gabriel.ferreira.ms_customer.domain.model.address.response.AddressResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final IAddressService _addressService;

    public AddressController(IAddressService addressService) {
        _addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponse> criarAddress(@RequestBody AddressRequest addressRequest){
        AddressResponse addressResponse = _addressService.criarAddress(addressRequest);
        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }
}
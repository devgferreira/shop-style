package com.gabriel.ferreira.ms_customer.appresentation;

import com.gabriel.ferreira.ms_customer.application.interfaces.IAddressService;
import com.gabriel.ferreira.ms_customer.domain.model.address.request.AddressRequest;
import com.gabriel.ferreira.ms_customer.domain.model.address.response.AddressResponse;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/id/{addressId}")
    public ResponseEntity<AddressResponse> atualizarAddress(@RequestBody AddressRequest addressRequest, @PathVariable Integer addressId){
        AddressResponse addressResponse = _addressService.atualizarAddressPorId(addressRequest, addressId);
        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }
    @DeleteMapping("/id/{addressId}")
    public ResponseEntity<String> deletarAddress(@PathVariable Integer addressId){
        _addressService.deleterAddressPorId(addressId);
        return new ResponseEntity<>("Address deletado com sucesso!", HttpStatus.OK);
    }
}
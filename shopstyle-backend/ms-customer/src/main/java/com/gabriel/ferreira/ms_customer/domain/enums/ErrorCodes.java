package com.gabriel.ferreira.ms_customer.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    CUSTOMER_NAO_ENCONTRADO("Customer não encontrado"),




    private final String mensagem;
}

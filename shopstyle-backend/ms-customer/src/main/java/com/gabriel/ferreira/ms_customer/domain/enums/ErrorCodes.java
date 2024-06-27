package com.gabriel.ferreira.ms_customer.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    CUSTOMER_NAO_ENCONTRADO("Customer não encontrado"),
    CUSTOMER_INVALIDO("Por favor, preencha todos os campos"),
    CUSTOMER_FIRST_NAME_INVALIDO("O First Name precisa contem 3 ou mais caracteres");



    private final String mensagem;
}

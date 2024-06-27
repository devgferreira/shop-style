package com.gabriel.ferreira.ms_customer.infra.exception;

import lombok.Data;

@Data
public class ExceptionResponse {
    private final String code;
    private final String mensagem;

    public ExceptionResponse(final ErrorCodes errorCodes, String details){
        this.code = errorCodes.name();
        this.mensagem = errorCodes.getMensagem();
    }
}

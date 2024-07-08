package com.gabriel.ferreira.ms_customer.infra.exception.token;

import com.gabriel.ferreira.ms_customer.infra.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class TokenCriarException extends RuntimeException{
    private final ExceptionResponse exceptionResponse;

    public TokenCriarException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}

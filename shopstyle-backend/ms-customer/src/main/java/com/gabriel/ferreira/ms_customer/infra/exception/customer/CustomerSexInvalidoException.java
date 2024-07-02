package com.gabriel.ferreira.ms_customer.infra.exception.customer;

import com.gabriel.ferreira.ms_customer.infra.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class CustomerSexInvalidoException extends RuntimeException{
    private final ExceptionResponse exceptionResponse;

    public CustomerSexInvalidoException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}

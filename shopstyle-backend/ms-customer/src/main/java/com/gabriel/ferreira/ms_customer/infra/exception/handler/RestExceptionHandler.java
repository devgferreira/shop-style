package com.gabriel.ferreira.ms_customer.infra.exception.handler;

import com.gabriel.ferreira.ms_customer.infra.exception.ExceptionResponse;
import com.gabriel.ferreira.ms_customer.infra.exception.customer.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.gabriel.ferreira.ms_customer.domain.enums.ErrorCodes;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomerCpfInvalidoException.class)
    public final ResponseEntity<Object> handleCustomerCpfInvalidoException(CustomerCpfInvalidoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CUSTOMER_CPF_INVALIDO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }
    @ExceptionHandler(CustomerCpfJaExisteException.class)
    public final ResponseEntity<Object> handleFuncionarioCustomerCpfJaExisteException(CustomerCpfJaExisteException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CUSTOMER_CPF_JA_EXISTE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }
    @ExceptionHandler(CustomerEmailInvalidoException.class)
    public final ResponseEntity<Object> handleCustomerEmailInvalidoException(CustomerEmailInvalidoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CUSTOMER_EMAIL_INVALIDO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }
    @ExceptionHandler(CustomerEmailJaExisteException.class)
    public final ResponseEntity<Object> handleCustomerEmailJaExisteException(CustomerEmailJaExisteException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CUSTOMER_EMAIL_JA_EXISTE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }
    @ExceptionHandler(CustomerFirstNameInvalidoException.class)
    public final ResponseEntity<Object> handleCustomerFirstNameInvalidoException(CustomerFirstNameInvalidoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CUSTOMER_FIRST_NAME_INVALIDO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }
}

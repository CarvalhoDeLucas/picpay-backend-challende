package com.carvalhodelucas.picpay_backend_challenge.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Quando der uma exception no controller ele ira cair aqui, onde a exception foi mapeada
@ControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Object> handler(InvalidTransactionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}

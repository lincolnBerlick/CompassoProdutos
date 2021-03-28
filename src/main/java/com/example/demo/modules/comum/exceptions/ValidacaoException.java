package com.example.demo.modules.comum.exceptions;

public class ValidacaoException extends RuntimeException {

    public ValidacaoException(String message) {
        super(message);
    }

    public ValidacaoException(String message, Exception ex) {
        super(message, ex);
    }
}

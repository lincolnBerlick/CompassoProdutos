package com.example.demo.modules.comum.exceptions.controller;

import com.example.demo.modules.comum.exceptions.ValidacaoException;
import com.example.demo.modules.comum.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class ExceptionHandlingController {

    private static Integer BAD_REQUEST_ERROR = HttpStatus.BAD_REQUEST.value();

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidacaoException.class)
    @ResponseBody
    public List<ErrorMessage> message(ValidacaoException validacaoException) {
        return List.of(new ErrorMessage(BAD_REQUEST_ERROR, validacaoException.getMessage()));
    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ExceptionHandler
//    @ResponseBody
//    public List<ErrorMessage> messageGeneric(Exception ex) {
//        return List.of(new ErrorMessage(ex.));
//    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public List<ErrorMessage> message(MethodArgumentNotValidException ex) {
        var campoErro = Optional.ofNullable(ex.getFieldError().getField())
                .orElse(ex.getObjectName());

        return List.of(new ErrorMessage(BAD_REQUEST_ERROR,
                String.format("Favor informar o campo {%s} ", campoErro.toUpperCase())));
    }



}

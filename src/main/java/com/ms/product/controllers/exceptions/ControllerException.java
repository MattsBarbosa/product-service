package com.ms.product.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerException {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(MethodArgumentNotValidException.class) // << aqui escolhe os tipos de erros que ira lidar
    public List<ErrorHandle> handle(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();

        List<ErrorHandle> list = new ArrayList<>();

        fieldErrorList.forEach(error ->
                list.add(new ErrorHandle(error.getField(),
                        error.getDefaultMessage())));

        return list;
    }
}

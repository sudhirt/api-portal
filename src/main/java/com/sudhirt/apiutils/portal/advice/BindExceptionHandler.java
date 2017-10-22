package com.sudhirt.apiutils.portal.advice;

import com.sudhirt.apiutils.portal.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BindExceptionHandler {

    @ExceptionHandler({ BindException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleBindException(BindException ex, WebRequest request) {
        Error exception = new Error("INVALID_INPUT");
        ex.getAllErrors().forEach(e -> {
            exception.addDetail(((FieldError) e).getField() + ": " + e.getDefaultMessage());
        });
        return exception;
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Error exception = new Error("INVALID_INPUT");
        ex.getBindingResult().getAllErrors().forEach(e -> {
            exception.addDetail(((FieldError) e).getField() + ": " + e.getDefaultMessage());
        });
        return exception;
    }
}

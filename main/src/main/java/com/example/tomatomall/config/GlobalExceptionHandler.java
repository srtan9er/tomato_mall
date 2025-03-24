package com.example.tomatomall.config;

import com.example.tomatomall.vo.Response;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public Response handleValidationExceptions(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String defaultMessage = fieldError != null ? fieldError.getDefaultMessage() : "参数验证失败";
        return Response.buildFailure(defaultMessage, "400");
    }

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception ex) {
        return Response.buildFailure(ex.getMessage(), "500");
    }
}
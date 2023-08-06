package com.turkcell.socceronlinemanagement.advice;


import com.turkcell.socceronlinemanagement.advice.exception.BusinessException;
import com.turkcell.socceronlinemanagement.common.constants.ExceptionTypes;
import com.turkcell.socceronlinemanagement.util.result.ExceptionResult;
import org.modelmapper.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//controllerdan gelebilecek her hatayı dinliyor
@RestControllerAdvice //hatayı dinliyor ve buraya getiriyor
public class RestExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400 : istek geçersiz olduğunda
    public ExceptionResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) { //bir yöntem argümanı geçersiz olduğunda atılan özel bir istisnadır
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ExceptionResult<>(ExceptionTypes.Exception.Validation, validationErrors);
    }

    @ExceptionHandler// gelen hatayı yönetiyor
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // 422 : işlenemeyen varlık
    public ExceptionResult<Object> handleValidationException(ValidationException exception) {  //doğrulama hatası
        return new ExceptionResult<>(ExceptionTypes.Exception.Validation, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // 422 : işlenemeyen varlık
    public ExceptionResult<Object> handleBusinessException(BusinessException exception) { //iş  hatası
        return new ExceptionResult<>(ExceptionTypes.Exception.Business, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT) // 409 : istekle çelişen bir durum
    public ExceptionResult<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) { //veritabanı bütünlüğü tutarsızlığı
        return new ExceptionResult<>(ExceptionTypes.Exception.DataIntegrityViolation, exception.getMessage());

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500 : sunucu hatası
    public ExceptionResult<Object> handleRuntimeException(RuntimeException exception) { //çalışma zamanı hatası
        return new ExceptionResult<>(ExceptionTypes.Exception.Runtime, exception.getMessage());
    }
}



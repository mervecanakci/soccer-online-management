package com.turkcell.socceronlinemanagement.core.exceptions;

public class BusinessException extends RuntimeException { //iş hataları için kullanılan sınıf
    public BusinessException(String message) {
        super(message);
    }
}

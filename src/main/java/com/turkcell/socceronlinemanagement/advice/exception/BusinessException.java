package com.turkcell.socceronlinemanagement.advice.exception;

public class BusinessException extends RuntimeException { //iş hataları için kullanılan sınıf
    public BusinessException(String message) {
        super(message);
    }
}

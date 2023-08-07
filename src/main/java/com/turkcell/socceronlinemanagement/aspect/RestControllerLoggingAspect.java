package com.turkcell.socceronlinemanagement.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j // slf4j annotation'ı ile loglama yapabilmek için gerekli olan logger'ı oluşturuyoruz
@Aspect // Aspect annotation'ı ile aspect olduğunu belirtiyoruz, aspect; bir methodun başında, sonunda, hata verdiğinde çalışan methodlardır
@Component // Component annotation'ı ile Spring'e bu class'ın bir bean olduğunu belirtiyoruz
public class RestControllerLoggingAspect {
    @AfterReturning("within(@org.springframework.web.bind.annotation.RestController *)") // RestController annotation'ı olan class'ların methodlarının sonunda çalışır
    public void log(JoinPoint joinPoint) { //joinPoint classın içinden bilgi alınır yani hangi methodun çalıştığı, hangi class'ta olduğu gibi
        String className = joinPoint.getTarget().getClass().getSimpleName(); // class adını alıyor
        String methodName = joinPoint.getSignature().getName(); // method adını alıyor
       // String line = String.valueOf(throwable.getStackTrace()[0].getLineNumber());

        String message = "Class: " + className + " Method: " + methodName +  " is called"; // mesajı oluşturuyor
        log.info(message + "\n"); // mesajı logluyor
    }
}

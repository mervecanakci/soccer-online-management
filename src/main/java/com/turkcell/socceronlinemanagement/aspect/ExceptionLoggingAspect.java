package com.turkcell.socceronlinemanagement.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspect { // ExceptionLoggingAspect class'ı, ExceptionHandler annotation'ı olan methodların hata vermesi durumunda çalışır
    private static final Logger log = LoggerFactory.getLogger(ExceptionLoggingAspect.class); // loglama yapabilmek için gerekli olan logger'ı oluşturuyoruz

    @AfterReturning(value = "@annotation(org.springframework.web.bind.annotation.ExceptionHandler)", returning = "response") // ExceptionHandler annotation'ı olan methodların sonunda çalışır
    public void logAfterReturning(JoinPoint joinPoint, Object response) {
        String logMessage = createLogMessage(joinPoint);
        log.error(String.format("%s => %s\n", logMessage, response)); // loglama yapılıyor: response da loglanıyor yani method'un dönüş değeri de loglanıyor
    }

    @AfterThrowing(pointcut = "@annotation(org.springframework.web.bind.annotation.ExceptionHandler)", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String logMessage = createLogMessage(joinPoint);
        log.error(String.format("Error in %s\n", logMessage), exception); // lüstteki method'dan farklı olarak exception da loglanıyor yani hatanın neden kaynaklandığı da loglanıyor
    }

    private String createLogMessage(JoinPoint joinPoint) { // loglanacak mesajı oluşturuyor
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        return String.format("%s.%s", className, methodName);
    }
}

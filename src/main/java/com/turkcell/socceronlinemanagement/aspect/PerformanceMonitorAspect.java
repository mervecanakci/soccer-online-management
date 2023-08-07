package com.turkcell.socceronlinemanagement.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceMonitorAspect { // PerformanceMonitorAspect class'ı, RestController annotation'ı olan methodların çalışma sürelerini loglar
    @Around("within(@org.springframework.web.bind.annotation.RestController *)") // RestController annotation'ı olan class'ların methodlarının başında ve sonunda çalışır
    public Object monitorExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis(); // methodun başlangıç zamanını alıyor
        Object result; // methodun dönüş değerini tutacak olan değişken

        try {
            result = joinPoint.proceed();
        } finally {
            long duration = calculateDuration(startTime); // methodun çalışma süresini hesaplıyor

            if (isDurationExceeded(duration)) { // methodun çalışma süresi 1 saniyeyi geçtiyse uyarı veriyor
                logWarning(joinPoint, duration);
            }
        }

        return result;
    }

    private long calculateDuration(long startTime) {
        long endTime = System.currentTimeMillis(); // methodun bitiş zamanını alıyor
        return endTime - startTime;
    }

    private boolean isDurationExceeded(long duration) {
        return duration > 1000; // methodun çalışma süresi 1 saniyeyi geçtiyse true döndürüyor
    }

    private void logWarning(ProceedingJoinPoint joinPoint, long duration) {
        String message = String.format("%s.%s took longer than 1 seconds to execute. Duration: %d ms\n", // mesaj oluşuyor
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                duration);
        log.warn(message);
    }
}
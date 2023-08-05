package com.turkcell.socceronlinemanagement.core.utils.results;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResult<T> { //hata mesajlarını döndürmek için kullanılan sınıf


    private LocalDateTime timestamp; //hata zamanı
    private String type; //hata tipi
    private T message; //hata mesajı

    public ExceptionResult(String type, T message) {
        timestamp = LocalDateTime.now();
        this.type = type;
        this.message = message;
    }

}

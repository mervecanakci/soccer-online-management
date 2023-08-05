package com.turkcell.socceronlinemanagement.configuration.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig { //modelMapper: bir sınıfın bir başka sınıfa dönüştürülmesini sağlar
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}

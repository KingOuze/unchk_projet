package com.example.tuto.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        // Exemple de mapping personnalisé
        modelMapper.addConverter(new AbstractConverter<LocalDate, Date>() {
            protected Date convert(LocalDate source) {
                return Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
            }
        });
        
    
        
        return modelMapper;
    }

    
}

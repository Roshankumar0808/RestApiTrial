package com.RESTAPI1.RESTAPI1.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperconfig {
    @Bean
    public ModelMapper getmodelmapper(){
        return new ModelMapper();
    }
}

package com.mai.solar.energyControl.configs;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GSONConfig {

    @Bean
    public Gson create(){
        return new Gson();
    }

}

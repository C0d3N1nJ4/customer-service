package com.application.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminGreetingConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> System.out.println("Hello Customer Admin");
    }
}

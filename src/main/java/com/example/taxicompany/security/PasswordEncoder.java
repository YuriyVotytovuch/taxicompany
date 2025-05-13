package com.example.taxicompany.security;

import org.springframework.context.annotation.Bean;

public class PasswordEncoder {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new PasswordEncoder();
    }
}

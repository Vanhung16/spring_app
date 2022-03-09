package com.hunguyen.spring_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {
     @Bean
     BCryptPasswordEncoder getBCryptpasswordEncoder() {
         return new BCryptPasswordEncoder();
     }

}

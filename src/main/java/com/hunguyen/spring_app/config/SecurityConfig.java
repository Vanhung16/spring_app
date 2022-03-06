package com.hunguyen.spring_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
     @Bean
     BCryptPasswordEncoder bCryptPasswordEncoder(){
         return new BCryptPasswordEncoder();
     }

}

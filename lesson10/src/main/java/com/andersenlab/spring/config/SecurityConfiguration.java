package com.andersenlab.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        try {
            return httpSecurity.authorizeHttpRequests(registry -> registry
                            .anyRequest().authenticated())
                    .formLogin(Customizer.withDefaults())
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        throw new SecurityException("Security Exception");
    }
}

package com.andersenlab.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfiguration {
    @Value("${app.security.login}")
    private String login;
    @Value("${app.security.password}")
    private String password;
    @Value("${app.security.role}")
    private String role;

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

    @Bean
    UserDetailsService userDetailsService() {
        return username -> new User(login, password, List.of(new SimpleGrantedAuthority(role)));
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return String.valueOf(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
        };
    }
}



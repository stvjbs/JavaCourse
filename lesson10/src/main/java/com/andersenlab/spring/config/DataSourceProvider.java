package com.andersenlab.spring.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name = "app.createDataSource")
public class DataSourceProvider {
    private static final String url = "jdbc:postgresql://localhost:5432/my_ticket_service_db";
    private static final String user = "user";
    private static final String password = "password";

    @Bean
    public DataSource getDataSource() {
        return new DriverManagerDataSource(url, user, password);
    }
}
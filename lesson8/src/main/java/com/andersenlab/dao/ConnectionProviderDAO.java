package com.andersenlab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProviderDAO {
    private static final String url = "jdbc:postgresql://localhost:5432/my_ticket_service_db";
    private static final String user = "user";
    private static final String password = "password";
    Connection connection;

    public ConnectionProviderDAO() {
        try {
            this.connection = DriverManager.getConnection(
                    url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

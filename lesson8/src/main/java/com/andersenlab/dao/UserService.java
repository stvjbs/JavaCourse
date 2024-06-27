package com.andersenlab.dao;

import com.andersenlab.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private final String url = "jdbc:postgresql://localhost:5432/my_ticket_service_db";
    private final String user = "user";
    private final String password = "password";
    Connection connection;

    public UserService() {
        try {
            this.connection = DriverManager.getConnection(
                    url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean saveUser(User user) {
        String insertQuery = "INSERT INTO users (name, creation_date) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setTimestamp(2, user.getCreationDate());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public User getUserById(int id) {
        String selectQuery = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setCreationDate(resultSet.getTimestamp("creation_date"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalArgumentException("No such user");
    }
}

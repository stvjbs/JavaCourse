package com.andersenlab.dao;

import com.andersenlab.dao.impl.UserCRUDable;
import com.andersenlab.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends ConnectionProviderDAO implements UserCRUDable {

    @Override
    public void saveUser(User user) {
        String insertQuery = "INSERT INTO users (name, creation_date) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setTimestamp(2, user.getCreationDate());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
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

    @Override
    public void deleteUserById(int id) {
        getUserById(id);
        String deleteQuery = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.printf("User with id {%s} deleted.\n", id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

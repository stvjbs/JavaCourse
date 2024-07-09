package com.andersenlab.spring.service;

import com.andersenlab.spring.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    User updateUserName(int id, String name);

    void deleteUser(int id);
}

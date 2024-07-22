package com.andersenlab.spring.service;

import com.andersenlab.spring.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto user);

    UserDto getUserById(int id);

    List<UserDto> getAllUsers();

    UserDto updateUserName(int id, String name);

    void deleteUser(int id);
}

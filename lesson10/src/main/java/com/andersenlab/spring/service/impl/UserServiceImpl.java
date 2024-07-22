package com.andersenlab.spring.service.impl;

import com.andersenlab.spring.dto.UserDto;
import com.andersenlab.spring.dto.dtoMapper.UserDtoMapper;
import com.andersenlab.spring.entity.User;
import com.andersenlab.spring.repository.UserRepository;
import com.andersenlab.spring.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto saveUser(UserDto user) {
        userRepository.save(UserDtoMapper.toUser(user));
        return user;
    }

    @Override
    public UserDto getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return UserDtoMapper.toUserDto(user.get());
        }
        throw new IllegalArgumentException("User not found");
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        if (!users.isEmpty()) {
            for (User user : users) {
                userDtos.add(UserDtoMapper.toUserDto(user));
            }
            return userDtos;
        }
        throw new IllegalArgumentException("Tickets not found");
    }

    @Override
    public void deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            System.out.println("User deleted successfully");
        } else throw new IllegalArgumentException("User not found");
    }

    @Override
    public UserDto updateUserName(int id, String name) {
        Optional<User> newUser = userRepository.findById(id);
        if (newUser.isPresent()) {
            newUser.get().setName(name);
            return UserDtoMapper.toUserDto(userRepository.save(newUser.get()));
        }
        throw new IllegalArgumentException("User not found");
    }

    public UserDto updateUserStatus(int id) {
        Optional<User> newUser = userRepository.findById(id);
        if (newUser.isPresent()) {
            newUser.get().setUserStatus(true);
            return UserDtoMapper.toUserDto(userRepository.save(newUser.get()));
        }
        throw new IllegalArgumentException("User not found");
    }

    public List<User> getUsersFromFile(Resource resource) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resource.getInputStream(),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("File not found");
    }
}

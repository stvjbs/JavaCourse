package com.andersenlab.spring.service.impl;

import com.andersenlab.spring.entity.User;
import com.andersenlab.spring.repository.TicketRepository;
import com.andersenlab.spring.repository.UserRepository;
import com.andersenlab.spring.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public UserServiceImpl(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int id) {
        ticketRepository.findByUserId(id).forEach(x -> ticketRepository.deleteById(x.getId()));
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserName(int id, String name) {
        User newUser = userRepository.findById(id).orElse(null);
        if (newUser != null) {
            newUser.setName(name);
            return userRepository.save(newUser);
        }
        throw new IllegalArgumentException("User not found");
    }
}

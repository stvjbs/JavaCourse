package com.andersenlab;

import com.andersenlab.dao.TicketService;
import com.andersenlab.dao.UserService;
import com.andersenlab.entity.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        TicketService ticketService = new TicketService();
        UserService userService = new UserService();
        User user = new User();
        user.setName("Steve");

        userService.saveUser(user);
        System.out.println(userService.getUserById(42));
    }
}
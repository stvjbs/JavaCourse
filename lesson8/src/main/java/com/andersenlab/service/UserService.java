package com.andersenlab.service;

import com.andersenlab.dao.TicketDAO;
import com.andersenlab.dao.UserDAO;
import com.andersenlab.dao.impl.UserCRUDable;
import com.andersenlab.entity.Ticket;
import com.andersenlab.entity.User;

import java.util.List;

public class UserService implements UserCRUDable {
    private UserDAO userDAO;
    private TicketDAO ticketDAO;

    public UserService() {
        userDAO = new UserDAO();
        ticketDAO = new TicketDAO();
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void deleteUserById(int id) {
        List<Ticket> ticketsDeletingUser = ticketDAO.getTicketsByUserId(id);
        ticketsDeletingUser.forEach(ticket -> ticketDAO.deleteTicketById(ticket.getId()));
        userDAO.deleteUserById(id);
    }
}

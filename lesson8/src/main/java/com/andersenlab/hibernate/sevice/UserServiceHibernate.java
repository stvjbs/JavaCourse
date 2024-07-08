package com.andersenlab.hibernate.sevice;

import com.andersenlab.dao.impl.UserCRUDable;
import com.andersenlab.entity.TicketType;
import com.andersenlab.entity.User;
import com.andersenlab.hibernate.dao.TicketDAOHibernate;
import com.andersenlab.hibernate.dao.UserDAOHibernate;
import jakarta.transaction.Transactional;

public class UserServiceHibernate implements UserCRUDable {
    UserDAOHibernate userDAO;
    TicketDAOHibernate ticketDAO;

    public UserServiceHibernate() {
        userDAO = new UserDAOHibernate();
        ticketDAO = new TicketDAOHibernate();
    }

    @Override
    public boolean saveUser(User user) {
        return userDAO.saveUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        ticketDAO.getTicketsByUserId(id).forEach(x -> ticketDAO.deleteTicketById(x.getId()));
        userDAO.deleteUserById(id);
    }

    @Transactional
    public void updateUserAndUsersTickets(int userId,
                                          String newUserName, TicketType newTicketType) {
        userDAO.updateUser(userId, newUserName);
        ticketDAO.getTicketsByUserId(userId).forEach(x ->
                ticketDAO.updateTicketTypeByTicketId(x.getId(), newTicketType));
    }
}

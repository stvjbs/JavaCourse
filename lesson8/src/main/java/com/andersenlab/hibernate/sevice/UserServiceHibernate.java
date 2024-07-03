package com.andersenlab.hibernate.sevice;

import com.andersenlab.dao.impl.UserCRUDable;
import com.andersenlab.entity.User;
import com.andersenlab.hibernate.dao.TicketDAOHibernate;
import com.andersenlab.hibernate.dao.UserDAOHibernate;

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
    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
        ticketDAO.getTicketsByUserId(id)
                .forEach(x-> ticketDAO.deleteTicketById(x.getId()));
    }
}

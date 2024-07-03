package com.andersenlab;

import com.andersenlab.entity.Ticket;
import com.andersenlab.entity.TicketType;
import com.andersenlab.entity.User;
import com.andersenlab.hibernate.sevice.TicketServiceHibernate;
import com.andersenlab.hibernate.sevice.UserServiceHibernate;
import com.andersenlab.service.TicketService;
import com.andersenlab.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserServiceHibernate userServiceHibernate = new UserServiceHibernate();

        User user = new User();
        user.setName("Steve Jobs");
        User user2 = new User();
        user2.setName("Steve Wozniak");

        Ticket ticket1 = new Ticket();
        ticket1.setUserId(1);
        ticket1.setTicketType(TicketType.DAY);
        userServiceHibernate.saveUser(user);
        userServiceHibernate.saveUser(user2);
        TicketServiceHibernate ticketServiceHibernate = new TicketServiceHibernate();
        ticketServiceHibernate.saveTicket(ticket1);
    }
}
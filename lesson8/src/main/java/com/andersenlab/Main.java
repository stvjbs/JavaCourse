package com.andersenlab;

import com.andersenlab.entity.Ticket;
import com.andersenlab.entity.TicketType;
import com.andersenlab.entity.User;
import com.andersenlab.hibernate.sevice.TicketServiceHibernate;
import com.andersenlab.hibernate.sevice.UserServiceHibernate;

public class Main {
    public static void main(String[] args) {
        UserServiceHibernate userServiceHibernate = new UserServiceHibernate();
        TicketServiceHibernate ticketServiceHibernate = new TicketServiceHibernate();

        User user = new User();
        user.setName("Steve Jobs");
        User user2 = new User();
        user2.setName("Steve Wozniak");

        Ticket ticket = new Ticket();
        ticket.setTicketType(TicketType.MONTH);
        ticket.setUserId(1);
        Ticket ticket2 = new Ticket();
        ticket2.setTicketType(TicketType.YEAR);
        ticket2.setUserId(1);

        userServiceHibernate.saveUser(user);
        userServiceHibernate.saveUser(user2);
        ticketServiceHibernate.saveTicket(ticket);
        ticketServiceHibernate.saveTicket(ticket2);
        userServiceHibernate.getUserById(1);
        userServiceHibernate
                .updateUserAndUsersTickets(21, "Steven Spielberg", TicketType.YEAR);
        userServiceHibernate.deleteUserById(1);
        ticketServiceHibernate.getTicketById(2);
        ticketServiceHibernate.getTicketsByUserId(2);
        ticketServiceHibernate.updateTicketTypeByTicketId(2, TicketType.MONTH);
        ticketServiceHibernate.deleteTicketById(2);
    }
}
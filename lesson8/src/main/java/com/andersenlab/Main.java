package com.andersenlab;

import com.andersenlab.entity.Ticket;
import com.andersenlab.entity.TicketType;
import com.andersenlab.entity.User;
import com.andersenlab.service.TicketService;
import com.andersenlab.service.UserService;

public class Main {
    public static void main(String[] args) {
        TicketService ticketService = new TicketService();
        UserService userService = new UserService();
        User user = new User();
        user.setName("Steve");
        Ticket ticket1 = new Ticket();
        ticket1.setUserId(1);
        ticket1.setTicketType(TicketType.DAY);
        System.out.println(ticketService.saveTicket(ticket1));
    }
}
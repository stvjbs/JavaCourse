package com.andersenlab.spring;

import com.andersenlab.spring.entity.Ticket;
import com.andersenlab.spring.entity.User;
import com.andersenlab.spring.service.impl.TicketServiceImpl;
import com.andersenlab.spring.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MySpringBootApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MySpringBootApplication.class, args);

        TicketServiceImpl ticketService = context.getBean(TicketServiceImpl.class);
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);

        User user = new User();
        user.setName("Steve Jobs");
        User user2 = new User();
        user2.setName("Steve Wozniak");

        Ticket ticket = new Ticket();
        ticket.setTicketType(Ticket.TicketType.MONTH);
        ticket.setUserId(1);
        Ticket ticket2 = new Ticket();
        ticket2.setTicketType(Ticket.TicketType.YEAR);
        ticket2.setUserId(1);
        Ticket ticket3 = new Ticket();
        ticket3.setTicketType(Ticket.TicketType.WEEK);
        ticket3.setUserId(2);
        Ticket ticket4 = new Ticket();
        ticket4.setTicketType(Ticket.TicketType.DAY);
        ticket4.setUserId(2);

        userService.saveUser(user);
        userService.saveUser(user2);
        ticketService.saveTicket(ticket);
        ticketService.saveTicket(ticket2);
        ticketService.saveTicket(ticket3);
        System.out.println(userService.getUserById(1));
        System.out.println(ticketService.getTicketById(2));
        System.out.println(ticketService.getTicketsByUserId(2));
        userService
                .updateUserName(2, "Steven Spielberg");
        userService.deleteUser(1);
        ticketService.updateTicketType(3, Ticket.TicketType.MONTH);
        ticketService.deleteTicket(4);

    }
}

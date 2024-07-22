package com.andersenlab.spring;

import com.andersenlab.spring.entity.Ticket;
import com.andersenlab.spring.entity.User;
import com.andersenlab.spring.service.impl.TicketServiceImpl;
import com.andersenlab.spring.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

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
        ticket.setUser(user);
        Ticket ticket2 = new Ticket();
        ticket2.setTicketType(Ticket.TicketType.YEAR);
        ticket2.setUser(user);
        Ticket ticket3 = new Ticket();
        ticket3.setTicketType(Ticket.TicketType.WEEK);
        ticket3.setUser(user2);
        Ticket ticket4 = new Ticket();
        ticket4.setTicketType(Ticket.TicketType.DAY);
        ticket4.setUser(user2);

        Resource ticketsFile = context.getResource("classpath:tickets.json");
        Resource usersFile = context.getResource("classpath:users.json");

        userService.getUsersFromFile(usersFile).forEach(userService::saveUser);
        ticketService.getTicketsFromFile(ticketsFile).forEach(ticketService::saveTicketAndUpdateUserStatus);
    }
}

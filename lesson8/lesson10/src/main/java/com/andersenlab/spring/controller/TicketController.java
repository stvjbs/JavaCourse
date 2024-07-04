package com.andersenlab.spring.controller;

import com.andersenlab.spring.entity.Ticket;
import com.andersenlab.spring.entity.User;
import com.andersenlab.spring.service.impl.TicketServiceImpl;
import com.andersenlab.spring.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tickets")
public class TicketController {
    private TicketServiceImpl ticketService;
    private UserServiceImpl userService;

    public TicketController(TicketServiceImpl ticketService, UserServiceImpl userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @PostMapping()
    public Ticket createTicket(@RequestParam(name = "userId") int userId,
                               @RequestParam(name = "ticketType") Ticket.TicketType ticketType) {
        User user = userService.getUserById(userId);
        if (user != null) {
            Ticket ticket = new Ticket();
            ticket.setUserId(userId);
            ticket.setTicketType(ticketType);
            return ticketService.saveTicket(ticket);
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }
}

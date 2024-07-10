package com.andersenlab.spring.service;

import com.andersenlab.spring.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket saveTicket(Ticket ticket);

    Ticket getTicketById(int id);

    List<Ticket> getTicketsByUserId(int userId);

    List<Ticket> getAllTickets();

    void deleteTicket(int id);

    Ticket updateTicketType(int id, Ticket.TicketType type);
}

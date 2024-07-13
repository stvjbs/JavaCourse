package com.andersenlab.spring.service;

import com.andersenlab.spring.dto.TicketDto;
import com.andersenlab.spring.entity.Ticket;

import java.util.List;

public interface TicketService {

    TicketDto getTicketById(int id);

    List<TicketDto> getTicketsByUserId(int userId);

    List<TicketDto> getAllTickets();

    void deleteTicket(int id);

    Ticket updateTicketType(int id, Ticket.TicketType type);
}

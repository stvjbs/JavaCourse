package com.andersenlab.dao.impl;

import com.andersenlab.entity.Ticket;
import com.andersenlab.entity.TicketType;

import java.util.List;

public interface TicketCRUDable {
    boolean saveTicket(Ticket ticket);

    Ticket getTicketById(int id);

    List<Ticket> getTicketsByUserId(int userId);

    Ticket updateTicketTypeByTicketId(int ticketId, TicketType ticketType);

    void deleteTicketById(int id);
}

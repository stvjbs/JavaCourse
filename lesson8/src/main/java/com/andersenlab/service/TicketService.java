package com.andersenlab.service;

import com.andersenlab.dao.TicketDAO;
import com.andersenlab.dao.impl.TicketCRUDable;
import com.andersenlab.entity.Ticket;
import com.andersenlab.entity.TicketType;

import java.util.List;

public class TicketService implements TicketCRUDable {
    private final TicketDAO ticketDAO;

    public TicketService() {
        ticketDAO = new TicketDAO();
    }

    @Override
    public boolean saveTicket(Ticket ticket) {
        return ticketDAO.saveTicket(ticket);
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketDAO.getTicketById(id);
    }

    @Override
    public List<Ticket> getTicketsByUserId(int userId) {
        return ticketDAO.getTicketsByUserId(userId);
    }

    @Override
    public Ticket updateTicketTypeByTicketId(int ticketId, TicketType ticketType) {
        return ticketDAO.updateTicketTypeByTicketId(ticketId, ticketType);
    }

    @Override
    public void deleteTicketById(int id) {
        ticketDAO.deleteTicketById(id);
    }

}

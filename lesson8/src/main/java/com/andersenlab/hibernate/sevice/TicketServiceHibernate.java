package com.andersenlab.hibernate.sevice;

import com.andersenlab.dao.impl.TicketCRUDable;
import com.andersenlab.entity.Ticket;
import com.andersenlab.entity.TicketType;
import com.andersenlab.hibernate.dao.TicketDAOHibernate;

import java.util.List;

public class TicketServiceHibernate implements TicketCRUDable {
    TicketDAOHibernate ticketDAOHibernate = new TicketDAOHibernate();

    @Override
    public boolean saveTicket(Ticket ticket) {
        return ticketDAOHibernate.saveTicket(ticket);
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketDAOHibernate.getTicketById(id);
    }

    @Override
    public List<Ticket> getTicketsByUserId(int userId) {
        return ticketDAOHibernate.getTicketsByUserId(userId);
    }

    @Override
    public Ticket updateTicketTypeByTicketId(int ticketId, TicketType ticketType) {
        return ticketDAOHibernate.updateTicketTypeByTicketId(ticketId, ticketType);
    }

    @Override
    public void deleteTicketById(int id) {
        ticketDAOHibernate.deleteTicketById(id);
    }
}

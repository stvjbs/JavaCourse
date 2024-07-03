package com.andersenlab.hibernate.dao;

import com.andersenlab.dao.impl.TicketCRUDable;
import com.andersenlab.entity.Ticket;
import com.andersenlab.entity.TicketType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TicketDAOHibernate implements TicketCRUDable {
    @Override
    public boolean saveTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(ticket);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public Ticket getTicketById(int id) {
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(Ticket.class, id);
    }

    @Override
    public List<Ticket> getTicketsByUserId(int userId) {
        String selectQuery = "FROM tickets WHERE userId = :userId";
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query<Ticket> query = session.createQuery(selectQuery, Ticket.class);
            query.setParameter("userId", userId);
            List<Ticket> tickets = query.getResultList();
            if (!tickets.isEmpty()) {
                return tickets;
            }
        } catch (Exception e) {
            System.out.println("Error fetching tickets: " + e.getMessage());
        }
        finally {
            tx.commit();
            session.close();
        }
        return new ArrayList<Ticket>();
    }

    @Override
    public Ticket updateTicketTypeByTicketId(int ticketId, TicketType ticketType) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, ticketId);
        if (ticketType != null) {
            ticket.setTicketType(ticketType);
            session.update(ticket);
            tx.commit();
            session.close();
            return ticket;
        }
        throw new IllegalArgumentException("No ticket found for id " + ticketId);
    }

    @Override
    public void deleteTicketById(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        if (ticket != null) {
            session.delete(ticket);
            tx.commit();
            session.close();
        }
        throw new IllegalArgumentException("No ticket found for id " + id);
    }
}


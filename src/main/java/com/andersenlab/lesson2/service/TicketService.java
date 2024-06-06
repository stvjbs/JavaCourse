package com.andersenlab.lesson2.service;

import com.andersenlab.lesson2.entity.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketService {
    private final Map<String, Ticket> tickets;

    public TicketService() {
        this.tickets = new HashMap<>();
    }

    public void createNRandomTickets(int n) {
        for (int i = 0; i < n; i++) {
            Ticket ticket = Ticket.createRandomTicket();
            tickets.put(ticket.getId(), ticket);
        }
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }
}
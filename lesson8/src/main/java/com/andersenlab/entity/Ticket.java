package com.andersenlab.entity;

import java.sql.Timestamp;

public class Ticket {
    private int id;
    private User user;
    private TicketType ticketType;
    private Timestamp creationDate;

    public Ticket(User user, TicketType ticketType, Timestamp creationDate) {
        this.user = user;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }
}

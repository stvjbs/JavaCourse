package com.andersenlab.entity;

import java.sql.Timestamp;

public class Ticket {
    private int id;
    private int userId;
    private TicketType ticketType;
    private Timestamp creationDate;

    public Ticket() {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", ticketType=" + ticketType +
                ", creationDate=" + creationDate +
                '}';
    }
}

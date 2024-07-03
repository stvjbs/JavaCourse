package com.andersenlab.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BusTicket {

    private static int nextId = 1;
    private final int id;
    private final TicketType ticketType;
    private final LocalDateTime startTime;
    private BigDecimal ticketPrice;

    private BusTicket(TicketType ticketType, LocalDateTime startTime) {
        this.id = nextId++;
        this.ticketType = ticketType;
        this.startTime = startTime;
    }

    public static BusTicket createTicket(TicketType ticketType, LocalDateTime startTime) {
        BusTicket busTicket = new BusTicket(ticketType, startTime);
        switch (ticketType) {
            case TYPE_20_MINUTES -> busTicket.setTicketPrice(TicketPrice.getPRICE_20_MINUTES());
            case TYPE_90_MINUTES -> busTicket.setTicketPrice(TicketPrice.getPRICE_90_MINUTES());
            case TYPE_DAY -> busTicket.setTicketPrice(TicketPrice.getPRICE_DAY());
            case TYPE_MONTH -> busTicket.setTicketPrice(TicketPrice.getPRICE_MONTH());
        }
        return busTicket;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public int getId() {
        return id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

package com.andersenlab.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusTicketTest {
    LocalDateTime dateTime;
    BusTicket busTicket;

    @BeforeEach
    public void setUp() {
        dateTime = LocalDateTime.of(2024, 6, 25, 20, 0);
        busTicket = BusTicket.createTicket(TicketType.TYPE_20_MINUTES, dateTime);
    }

    @Test
    void createTicketTestOfType() {
        assertEquals(busTicket.getTicketType(), TicketType.TYPE_20_MINUTES);
    }

    @Test
    void createTicketTestOfDate() {
        assertEquals(busTicket.getStartTime(), dateTime);
    }

    @Test
    void createTicketTestOfPrice() {
        assertEquals(busTicket.getTicketPrice(), TicketPrice.getPRICE_20_MINUTES());
    }

    @Test
    void createTicketTestOfId() {
        assertEquals(busTicket.getId(), 4);
    }
}
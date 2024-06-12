package com.andersenlab.lesson2;

import com.andersenlab.lesson2.entity.Ticket;
import com.andersenlab.lesson2.service.TicketService;
import com.andersenlab.lesson2.util.StadiumSector;

public class Main {
    static final String ID = "5Gsr";
    static final String VEGA_ARENA = "Vega Arena";
    static final long EVENT_DATE_TIMESTAMP = 1723240800;
    static final double MAX_BACKPACK_WEIGHT = 5.5;
    static final String EVENT_CODE = "001";
    static final int PRICE_IN_CENTS = 20000;

    public static void main(String[] args) {
        Ticket full = Ticket.createTicket(ID, VEGA_ARENA, EVENT_CODE,
                EVENT_DATE_TIMESTAMP, false,
                StadiumSector.B, MAX_BACKPACK_WEIGHT, PRICE_IN_CENTS);
        TicketService ticketService = new TicketService();
        ticketService.createNRandomTickets(9);
        ticketService.addTicket(full);
        ticketService.getTickets().values().forEach(System.out::println);
        System.out.println(ticketService.getTicketByID(full.getId()));
    }
}

package com.andersenlab.lesson2;

import com.andersenlab.lesson2.service.TicketService;

public class Main {

    public static void main(String[] args) {
        TicketService ticketService = new TicketService();
        ticketService.createNRandomTickets(10);
        ticketService.getTickets().values().forEach(System.out::println);

    }
}

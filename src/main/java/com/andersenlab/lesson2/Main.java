package com.andersenlab.lesson2;

import com.andersenlab.lesson2.entity.Ticket;
import com.andersenlab.lesson2.entity.user.Admin;
import com.andersenlab.lesson2.entity.user.Client;
import com.andersenlab.lesson2.util.StadiumSector;


public class Main {
    public static final String ID = "5Gsr";
    public static final String VEGA_ARENA = "Vega Arena";
    public static final long EVENT_DATE_TIMESTAMP = 1723240800;
    public static final double MAX_BACKPACK_WEIGHT = 5.5;
    public static final String EVENT_CODE = "001";
    public static final int PRICE_IN_CENTS = 20000;

    public static void main(String[] args) {
        Ticket empty = Ticket.createTicket();
        Ticket full = Ticket.createTicket(ID, VEGA_ARENA, EVENT_CODE,
                EVENT_DATE_TIMESTAMP, false,
                StadiumSector.B, MAX_BACKPACK_WEIGHT, PRICE_IN_CENTS);
        Ticket limited = Ticket.createTicket(VEGA_ARENA, EVENT_CODE, EVENT_DATE_TIMESTAMP);

        Admin admin = new Admin();
        Client client = new Client();

        System.out.println(empty);
        System.out.println(full);
        System.out.println(limited);

        full.share("+48346834858");
        full.print();

        admin.printRole();
        client.printRole();
        admin.checkTicket(client.getTicket());

    }
}

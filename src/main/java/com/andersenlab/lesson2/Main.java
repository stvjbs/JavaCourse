package com.andersenlab.lesson2;
import com.andersenlab.lesson2.entity.Ticket;
import com.andersenlab.lesson2.entity.user.Admin;
import com.andersenlab.lesson2.entity.user.Client;
import com.andersenlab.lesson2.entity.user.User;
import com.andersenlab.lesson2.util.StadiumSector;

import static com.andersenlab.lesson2.TicketService.*;

public class Main {
    public static void main(String[] args) {
        Ticket empty = Ticket.createTicket();
        Ticket full = Ticket.createTicket(ID, VEGA_ARENA, EVENT_CODE,
                EVENT_DATE_TIMESTAMP, false,
                StadiumSector.B, MAX_BACKPACK_WEIGHT, PRICE_IN_CENTS);
        Ticket limited = Ticket.createTicket(VEGA_ARENA, EVENT_CODE, EVENT_DATE_TIMESTAMP);

        User admin = new Admin();
        User client = new Client();

        System.out.println(empty);
        System.out.println(full);
        System.out.println(limited);

        full.share("+48346834858");
        full.print();

        admin.printRole();
        client.printRole();
        ((Admin) admin).checkTicket(((Client) client).getTicket());

    }
}

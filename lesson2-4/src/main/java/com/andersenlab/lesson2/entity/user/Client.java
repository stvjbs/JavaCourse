package com.andersenlab.lesson2.entity.user;

import com.andersenlab.lesson2.entity.Ticket;

public class Client extends User{
    @Override
    public void printRole() {
        System.out.println("Role of user is {Client}");
    }

    public Ticket getTicket() {
        return Ticket.createTicket();
    }
}

package com.andersenlab.lesson2.entity.user;

import com.andersenlab.lesson2.entity.Ticket;

public class Admin extends User {
    @Override
    public void printRole() {
        System.out.println("Role of user is {Admin}");
    }

    public void checkTicket(Ticket ticket){
        if(ticket == null){
            System.out.println("Ticket is null");
        }
        else System.out.println("Ticket is not null");
    }
}

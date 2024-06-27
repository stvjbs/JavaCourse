package com.andersenlab.dao;

import com.andersenlab.entity.Ticket;

public class TicketService {
    public String saveTicket(Ticket ticket) {
        return "INSERT INTO your_table_name (user_id, ticket_type, creation_date) VALUES (?, ?, ?)";
    }
}

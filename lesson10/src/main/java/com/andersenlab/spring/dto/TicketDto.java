package com.andersenlab.spring.dto;

import com.andersenlab.spring.entity.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TicketDto {
    @NonNull
    private int id;
    private UserDto user;
    private Ticket.TicketType ticketType;

}

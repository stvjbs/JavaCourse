package com.andersenlab.spring.dto;

import com.andersenlab.spring.entity.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDto {
    private int id;
    private UserDto userDto;
    private Ticket.TicketType ticketType;

}

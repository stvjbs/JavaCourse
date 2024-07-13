package com.andersenlab.spring.dto.dtoMapper;

import com.andersenlab.spring.dto.TicketDto;
import com.andersenlab.spring.entity.Ticket;

public class TicketDtoMapper {
    public static TicketDto toDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setUserDto(UserDtoMapper.toUserDto(ticket.getUser()));
        ticketDto.setTicketType(ticket.getTicketType());
        return ticketDto;
    }

    public static Ticket toTicket(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setUser(UserDtoMapper.toUser(ticketDto.getUserDto()));
        ticket.setTicketType(ticketDto.getTicketType());
        return ticket;
    }
}

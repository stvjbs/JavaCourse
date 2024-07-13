package com.andersenlab.spring.service.impl;

import com.andersenlab.spring.dto.TicketDto;
import com.andersenlab.spring.dto.dtoMapper.TicketDtoMapper;
import com.andersenlab.spring.entity.Ticket;
import com.andersenlab.spring.repository.TicketRepository;
import com.andersenlab.spring.service.TicketService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public TicketDto getTicketById(int id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return TicketDtoMapper.toDto(ticket.get());
        }
        throw new IllegalArgumentException("Ticket not found");
    }

    @Override
    public List<TicketDto> getTicketsByUserId(int userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        return getTicketDtos(tickets);
    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return getTicketDtos(tickets);
    }

    @Override
    public void deleteTicket(int id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            System.out.println("Ticket deleted successfully");
        } else throw new IllegalArgumentException("Ticket not found");
    }

    @Override
    public Ticket updateTicketType(int id, Ticket.TicketType type) {
        Optional<Ticket> newTicket = ticketRepository.findById(id);
        if (newTicket.isPresent()) {
            newTicket.get().setTicketType(type);
            return ticketRepository.save(newTicket.get());
        }
        throw new IllegalArgumentException("Ticket not found");
    }

    public TicketDto saveTicket(TicketDto ticketDto) {
        ticketRepository.save(TicketDtoMapper.toTicket(ticketDto));
        return ticketDto;
    }

    private List<TicketDto> getTicketDtos(List<Ticket> tickets) {
        List<TicketDto> ticketDtos = new ArrayList<>();
        if (!tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                ticketDtos.add(TicketDtoMapper.toDto(ticket));
            }
            return ticketDtos;
        }
        throw new IllegalArgumentException("Tickets not found");
    }

    public List<Ticket> getTicketsFromFile(Resource resource) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resource.getInputStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("File not found");
    }
}

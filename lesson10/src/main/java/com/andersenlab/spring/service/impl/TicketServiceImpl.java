package com.andersenlab.spring.service.impl;

import com.andersenlab.spring.entity.Ticket;
import com.andersenlab.spring.entity.User;
import com.andersenlab.spring.repository.TicketRepository;
import com.andersenlab.spring.repository.UserRepository;
import com.andersenlab.spring.service.TicketService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    @Value("${app.updateUserAndCreateTicketEnabled}")
    private boolean isOperationEnabled;

    public TicketServiceImpl(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public Ticket saveTicketAndUpdateUserStatus(Ticket ticket) {
        if (!isOperationEnabled) {
            throw new RuntimeException("Operation is not enabled");
        }
        User user = userRepository.findById(ticket.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserStatus(true);
        userRepository.save(user);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ticket> getTicketsByUserId(int userId) {
        return ticketRepository.findByUserId(userId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Ticket updateTicketType(int id, Ticket.TicketType type) {
        Ticket newTicket = ticketRepository.findById(id).orElse(null);
        if (newTicket != null) {
            newTicket.setTicketType(type);
            return ticketRepository.save(newTicket);
        }
        throw new IllegalArgumentException("Ticket not found");
    }

    public List<Ticket> getTicketsFromFile(Resource resource) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resource.getInputStream(),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("File not found");
    }
}

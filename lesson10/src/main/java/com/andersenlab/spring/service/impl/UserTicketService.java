package com.andersenlab.spring.service.impl;

import com.andersenlab.spring.dto.TicketDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserTicketService {
    private final UserServiceImpl userService;
    private final TicketServiceImpl ticketService;
    @Value("${app.updateUserAndCreateTicketEnabled}")
    private boolean isOperationEnabled;

    public UserTicketService(UserServiceImpl userService, TicketServiceImpl ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @Transactional
    public TicketDto saveTicketAndUpdateUserStatus(TicketDto ticket) {
        isOperationEnabled();
        userService.updateUserStatus(ticket.getUserDto().getId());
        ticketService.saveTicket(ticket);
        return ticket;
    }

    private void isOperationEnabled() {
        if (!isOperationEnabled) {
            throw new RuntimeException("Operation is not enabled");
        }
    }

}

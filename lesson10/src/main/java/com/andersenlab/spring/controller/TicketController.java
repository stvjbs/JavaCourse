package com.andersenlab.spring.controller;

import com.andersenlab.spring.dto.TicketDto;
import com.andersenlab.spring.service.impl.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketServiceImpl ticketService;

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable("ticketId") int ticketId) {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.getTicketById(ticketId));
    }
}

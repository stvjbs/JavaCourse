package com.andersenlab.spring.service.impl;

import com.andersenlab.spring.dto.TicketDto;
import com.andersenlab.spring.dto.dtoMapper.TicketDtoMapper;
import com.andersenlab.spring.entity.Ticket;
import com.andersenlab.spring.entity.User;
import com.andersenlab.spring.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {
    @Mock
    TicketRepository ticketRepository;
    @InjectMocks
    TicketServiceImpl ticketService;

    private Ticket ticket;
    private TicketDto ticketDto;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId(1);
        user.setName("John");
        user.setUserStatus(true);
        ticket = new Ticket();
        ticket.setId(1);
        ticket.setUser(user);
        ticket.setTicketType(Ticket.TicketType.DAY);
        ticketDto = TicketDtoMapper.toDto(ticket);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 100})
    void testGetTicketByIdPositiveAndNotFound(int id) {
        if (id == 1) {
            when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
            Optional<TicketDto> result = Optional.of(ticketService.getTicketById(id));
            assertEquals(ticketDto, result.get());
        } else {
            when(ticketRepository.findById(id)).thenReturn(Optional.empty());
            assertThrows(IllegalArgumentException.class, () -> ticketService.getTicketById(id));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 100})
    void testGetTicketsByUserIdPositiveAndNotFound(int userId) {
        if (userId == 1) {
            when(ticketRepository.findByUserId(userId)).thenReturn(List.of(ticket));
            List<TicketDto> result = ticketService.getTicketsByUserId(userId);
            assertFalse(result.isEmpty());
            assertEquals(ticketDto, result.getFirst());
        } else {
            when(ticketRepository.findByUserId(userId)).thenReturn(new ArrayList<>());
            assertThrows(IllegalArgumentException.class, () -> ticketService.getTicketsByUserId(userId));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 100, 0})
    void testGetAllTicketsPositiveAndNotFound(int amountOfTickets) {
        if (amountOfTickets > 0) {
            when(ticketRepository.findAll()).thenReturn(List.of(ticket));
            List<TicketDto> result = ticketService.getAllTickets();
            assertFalse(result.isEmpty());
            assertEquals(ticketDto, result.getFirst());
        }
        if (amountOfTickets == 0) {
            when(ticketRepository.findAll()).thenReturn(new ArrayList<>());
            Exception exception = assertThrows(IllegalArgumentException.class, () -> ticketService.getAllTickets());
            assertEquals("Tickets not found", exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testDeleteTicketPositiveAndNotFound(int id) {
        if (id == 1) {
            when(ticketRepository.existsById(id)).thenReturn(true);
            doNothing().when(ticketRepository).deleteById(id);
            ticketService.deleteTicket(id);
            verify(ticketRepository, times(1)).deleteById(id);
        } else {
            when(ticketRepository.existsById(id)).thenReturn(false);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> ticketService.deleteTicket(id));
            assertEquals("Ticket not found", exception.getMessage());
            verify(ticketRepository, times(0)).deleteById(id);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1, DAY",
            "2, MONTH"
    })
    void testUpdateTicketTypePositiveAndNotFound(int id, Ticket.TicketType newType) {
        if (id == 1) {
            when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
            ticket.setTicketType(newType);
            when(ticketRepository.save(ticket)).thenReturn(ticket);
            ticketService.updateTicketType(id, newType);
        } else {
            when(ticketRepository.findById(id)).thenReturn(Optional.empty());
            Exception exception = assertThrows(IllegalArgumentException.class, () -> ticketService.updateTicketType(id, newType));
            assertEquals("Ticket not found", exception.getMessage());
            verify(ticketRepository, times(0)).save(ticket);
        }
    }

    @Test
    void testSaveTicketPositive() {
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        TicketDto result = ticketService.saveTicket(ticketDto);
        assertEquals(ticketDto, result);
        verify(ticketRepository, times(1)).save(ticket);
    }

    @ParameterizedTest
    @MethodSource("resourceProvider")
    void testGetTicketsFromFilePositive(Resource resource) {
        List<Ticket> tickets = ticketService.getTicketsFromFile(resource);
        assertFalse(tickets.isEmpty());
        assertEquals(tickets.getFirst().getUser().getId(), 1);
    }

    static Stream<Arguments> resourceProvider() {
        return Stream.of(
                Arguments.of(new ClassPathResource("tests/tickets.csv")));
    }
}
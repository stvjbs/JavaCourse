package com.andersenlab.dao;

import com.andersenlab.EmbeddedPostgresDatabase;
import com.andersenlab.entity.Ticket;
import com.andersenlab.entity.TicketType;
import com.andersenlab.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketDAOTest {
    private EmbeddedPostgresDatabase db;
    UserDAO userDAO;
    TicketDAO ticketDAO;

    @BeforeEach
    void setUp() {
        db = new EmbeddedPostgresDatabase();
        userDAO = new UserDAO();
        ticketDAO = new TicketDAO();
        userDAO.connection = db.getConnection();
        ticketDAO.connection = db.getConnection();
    }

    @AfterEach
    void tearDown() {
        db.closeConnection();
    }

    @Test
    void saveTicketTrueTest() {
        User user = new User();
        user.setName("John Travolta");
        userDAO.saveUser(user);
        Ticket ticket = new Ticket();
        ticket.setTicketType(TicketType.DAY);
        ticket.setUserId(1);
        assertTrue(ticketDAO.saveTicket(ticket));
    }

    @Test
    void saveTicketUserNotFoundTest() {
        Ticket ticket = new Ticket();
        ticket.setTicketType(TicketType.DAY);
        ticket.setUserId(1);
        assertFalse(ticketDAO.saveTicket(ticket));
    }

    @Test
    void getTicketByIdNotNullTest() {
        User user = new User();
        user.setName("John Travolta");
        userDAO.saveUser(user);
        Ticket ticket = new Ticket();
        ticket.setTicketType(TicketType.DAY);
        ticket.setUserId(1);
        ticketDAO.saveTicket(ticket);
        assertNotNull(ticketDAO.getTicketById(1));
    }

    @Test
    void getTicketByIdNotFoundTest() {
        assertThrows(IllegalArgumentException.class, () -> ticketDAO.getTicketById(1));
    }

    @Test
    void getTicketsByUserIdCorrectTest() {
        User user1 = new User();
        user1.setName("John Travolta");
        User user2 = new User();
        user2.setName("Nikolas Cage");
        userDAO.saveUser(user1);
        userDAO.saveUser(user2);
        Ticket ticket1 = new Ticket();
        ticket1.setTicketType(TicketType.DAY);
        ticket1.setUserId(1);
        Ticket ticket2 = new Ticket();
        ticket2.setTicketType(TicketType.DAY);
        ticket2.setUserId(1);
        Ticket ticket3 = new Ticket();
        ticket3.setTicketType(TicketType.DAY);
        ticket3.setUserId(2);
        ticketDAO.saveTicket(ticket1);
        ticketDAO.saveTicket(ticket2);
        ticketDAO.saveTicket(ticket3);
        List<Ticket> ticketsOfUser1 = ticketDAO.getTicketsByUserId(1);
        List<Ticket> ticketsOfUser2 = ticketDAO.getTicketsByUserId(2);
        assertEquals(2, ticketsOfUser1.size());
        assertEquals(1, ticketsOfUser2.size());
    }

    @Test
    void getTicketsByUserIdUserNotFoundTest() {
        assertThrows(IllegalArgumentException.class, () -> ticketDAO.getTicketsByUserId(1));
    }

    @Test
    void updateTicketTypeByTicketIdCorrectTest() {
        User user = new User();
        user.setName("John Travolta");
        userDAO.saveUser(user);
        Ticket ticket = new Ticket();
        ticket.setTicketType(TicketType.DAY);
        ticket.setUserId(1);
        ticketDAO.saveTicket(ticket);
        ticketDAO.updateTicketTypeByTicketId(1, TicketType.YEAR);
        assertEquals(TicketType.YEAR, ticketDAO.getTicketById(1).getTicketType());
    }

    @Test
    void deleteTicketById() {
        User user = new User();
        user.setName("John Travolta");
        userDAO.saveUser(user);
        Ticket ticket = new Ticket();
        ticket.setTicketType(TicketType.DAY);
        ticket.setUserId(1);
        ticketDAO.saveTicket(ticket);
        assertNotNull(ticketDAO.getTicketById(1));
        ticketDAO.deleteTicketById(1);
        assertThrows(IllegalArgumentException.class, () -> ticketDAO.getTicketById(1));
    }
}
package com.andersenlab.service;

import com.andersenlab.entity.BusTicket;
import com.andersenlab.entity.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BusTicketServiceTest {
    BusTicketService busTicketService = new BusTicketService();;
    LocalDateTime dateTime = LocalDateTime.of(2024, 6, 25, 20, 0);
    BigDecimal priceA = new BigDecimal(200);
    BigDecimal priceB = new BigDecimal(400);

    @Test
    void createBusTicketNotNullTest() {
        BusTicket busTicket = busTicketService.createBusTicket(TicketType.TYPE_20_MINUTES, dateTime);
        assertNotNull(busTicket);
    }

    @Test
    void storeBusTicketTrueTest() {
        BusTicket busTicket = busTicketService.createBusTicket(TicketType.TYPE_20_MINUTES, dateTime);
        assertTrue(busTicketService.storeBusTicket(busTicket));
    }

    @Test
    void storeBusTicketExceptionTest() {
        BusTicket busTicket = busTicketService.createBusTicket(TicketType.TYPE_20_MINUTES, dateTime);
        busTicketService.storeBusTicket(busTicket);
        assertThrows(IllegalArgumentException.class, () -> busTicketService.storeBusTicket(busTicket));
    }

    @Test
    void removeBusTicketTrueTest() {
        BusTicket busTicket = busTicketService.createBusTicket(TicketType.TYPE_20_MINUTES, dateTime);
        busTicketService.storeBusTicket(busTicket);
        assertTrue(busTicketService.removeBusTicket(busTicket));
    }

    @Test
    void removeBusTicketExceptionTest() {
        BusTicket busTicket = busTicketService.createBusTicket(TicketType.TYPE_20_MINUTES, dateTime);
        assertThrows(IllegalArgumentException.class, () -> busTicketService.removeBusTicket(busTicket));
    }

    @Test
    void getBusTicketByIdTest() {
        BusTicket busTicket = busTicketService.createBusTicket(TicketType.TYPE_20_MINUTES, dateTime);
        int id = busTicket.getId();
        busTicketService.storeBusTicket(busTicket);
        assertEquals(busTicketService.getBusTicketById(id), busTicket);
    }

    @Test
    void getBusTicketByIdExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> busTicketService.getBusTicketById(67));
    }

    @Test
    void getBusTicketsByType() {
        BusTicketService newBusTicketService = new BusTicketService();
        BusTicket busTicket1 = newBusTicketService.createBusTicket(TicketType.TYPE_20_MINUTES, dateTime);
        BusTicket busTicket2 = newBusTicketService.createBusTicket(TicketType.TYPE_20_MINUTES, dateTime);
        newBusTicketService.storeBusTicket(busTicket1);
        newBusTicketService.storeBusTicket(busTicket2);
        List<BusTicket> list = List.of(busTicket1, busTicket2);
        assertEquals(list, newBusTicketService.getBusTicketsByType(TicketType.TYPE_20_MINUTES));
    }

    @Test
    void getBusTicketsByPrice() {
        BusTicketService newBusTicketService = new BusTicketService();
        BusTicket busTicket1 = newBusTicketService.createBusTicket(TicketType.TYPE_20_MINUTES, dateTime);
        BusTicket busTicket2 = newBusTicketService.createBusTicket(TicketType.TYPE_20_MINUTES, dateTime);
        newBusTicketService.storeBusTicket(busTicket1);
        newBusTicketService.storeBusTicket(busTicket2);
        List<BusTicket> list = List.of(busTicket1, busTicket2);
        assertEquals(list, newBusTicketService.getBusTicketsByPrice(priceA, priceB));
    }
}
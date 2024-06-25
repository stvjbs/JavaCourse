package com.andersenlab.service;

import com.andersenlab.entity.BusTicket;
import com.andersenlab.entity.TicketType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusTicketService {

    private final Map<Integer, BusTicket> busTickets;

    public BusTicketService() {
        this.busTickets = new HashMap<>();
    }

    public BusTicket createBusTicket(TicketType ticketType, LocalDateTime startDate) {
        return BusTicket.createTicket(ticketType, startDate);
    }

    public boolean storeBusTicket(BusTicket busTicket) {
        if (busTickets.containsKey(busTicket.getId())) {
            throw new IllegalArgumentException("BusTicket already exists");
        }
        busTickets.put(busTicket.getId(), busTicket);
        return true;
    }

    public boolean removeBusTicket(BusTicket busTicket) {
        if (busTickets.containsKey(busTicket.getId())) {
            busTickets.remove(busTicket.getId());
            return true;
        }
        throw new IllegalArgumentException("BusTicket does not exist");
    }

    public BusTicket getBusTicketById(int id) {
        if (busTickets.containsKey(id)) {
            return busTickets.get(id);
        }
        throw new IllegalArgumentException("BusTicket does not exist");
    }

    public List<BusTicket> getBusTicketsByType(TicketType ticketType) {
        List<BusTicket> result = new ArrayList<>();
        busTickets.values().stream().filter(x -> x.getTicketType().equals(ticketType)).forEach(result::add);
        return result;
    }

    public List<BusTicket> getBusTicketsByPrice(BigDecimal priceA, BigDecimal priceB) {
        List<BusTicket> result = new ArrayList<>();
        busTickets.values().stream()
                .filter(x -> x.getTicketPrice().compareTo(priceA) > 0 &&
                        x.getTicketPrice().compareTo(priceB) < 0)
                .forEach(result::add);
        return result;
    }
}

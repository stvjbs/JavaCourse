package com.andersenlab.service;

import com.andersenlab.entity.BusTicket;
import com.andersenlab.entity.TicketType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * <p>This class provides services related to bus tickets.</p>
 *
 * <p>It allows creating, storing, retrieving, and removing bus tickets.</p>
 *
 * <p>Usage example:</p>
 * <pre>{@code
 * BusTicketService ticketService = new BusTicketService();
 * BusTicket myTicket = ticketService.createBusTicket(TicketType.REGULAR, LocalDateTime.now());
 * ticketService.storeBusTicket(myTicket);
 * }</pre>
 */
public class BusTicketService {

    private final Map<Integer, BusTicket> busTickets;

    public BusTicketService() {
        this.busTickets = new HashMap<>();
    }

    public BusTicket createBusTicket(TicketType ticketType, LocalDateTime startDate) {
        return BusTicket.createTicket(ticketType, startDate);
    }

/**
 * This method store a ticket in a in-memory storage (HashMap<>).
 *
 * @param busTicket Ticket, which need to be saved.
 * @return {@code true}, if ticket successfully saved, else {@code false}.
 * @throws IllegalArgumentException If ticket with those ID already exists.
 */
    public boolean storeBusTicket(BusTicket busTicket) {
        if (busTickets.containsKey(busTicket.getId())) {
            throw new IllegalArgumentException("BusTicket already exists");
        }
        busTickets.put(busTicket.getId(), busTicket);
        return true;
    }

    /**
     * Removes a bus ticket from the collection.
     *
     * @param busTicket The bus ticket to remove.
     * @return {@code true} if the ticket is successfully removed, otherwise an exception is thrown.
     * @throws IllegalArgumentException If a ticket with the specified ID does not exist.
     */
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

    /**
     * Retrieves a list of bus tickets with prices falling within the specified range.
     *
     * @param priceA The lower bound of the price range (exclusive).
     * @param priceB The upper bound of the price range (exclusive).
     * @return A list of bus tickets whose prices are greater than `priceA` and less than `priceB`.
     */
    public List<BusTicket> getBusTicketsByPrice(BigDecimal priceA, BigDecimal priceB) {
        List<BusTicket> result = new ArrayList<>();
        busTickets.values().stream()
                .filter(x -> x.getTicketPrice().compareTo(priceA) > 0 &&
                        x.getTicketPrice().compareTo(priceB) < 0)
                .forEach(result::add);
        return result;
    }
}

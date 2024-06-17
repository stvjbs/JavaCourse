package com.andersenlab;

import java.util.EnumSet;
import java.util.List;

public class Validator {
    public List<BusTicket> validateStartDateExisting(List<BusTicket> list) {
        EnumSet<TicketType> requiredDataTypes = EnumSet.of(TicketType.DAY, TicketType.WEEK, TicketType.YEAR);
        EnumSet<TicketType> unrequiredDataTypes = EnumSet.of(TicketType.MONTH, TicketType.PRIME);
        return list.stream()
                .filter(ticket -> (requiredDataTypes.contains(ticket.getTicketType()) &&
                        ticket.getStartDate() != null && !ticket.getStartDate().isBlank()) ||
                        unrequiredDataTypes.contains(ticket.getTicketType()))
                .toList();
    }

    public List<BusTicket> validatePriceExisting(List<BusTicket> list) {

        return list.stream()
                .filter(ticket -> !priceZeroNullOrBlank(ticket))
                .toList();
    }



    private boolean priceZeroNullOrBlank(BusTicket ticket) {
        return ticket.getPrice() == null || ticket.getPrice().isBlank() ||
                ticket.getPrice().equals("0");
    }
}
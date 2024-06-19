package com.andersenlab.util;

import com.andersenlab.entity.BusTicket;
import com.andersenlab.entity.TicketType;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;

public class Validator {
    public List<BusTicket> validateStartDateExistsInRequiredTypes(List<BusTicket> list) {
        EnumSet<TicketType> requiredDataTypes = EnumSet.of(TicketType.DAY, TicketType.WEEK, TicketType.YEAR);
        EnumSet<TicketType> unrequiredDataTypes = EnumSet.of(TicketType.MONTH);
        return list.stream()
                .filter(ticket -> (requiredDataTypes.contains(ticket.getTicketType()) &&
                        ticket.getStartDate() != null && !ticket.getStartDate().isBlank()) ||
                        unrequiredDataTypes.contains(ticket.getTicketType()))
                .toList();
    }

    public List<BusTicket> validatePriceExisting(List<BusTicket> list) {
        return list.stream()
                .filter(ticket -> !priceZeroNullOrBlank(ticket.getPrice()))
                .toList();
    }

    public List<BusTicket> validateStartDateNotInFuture(List<BusTicket> list) {
        return list.stream()
                .filter(busTicket -> stringNotNullOrBlank(busTicket.getStartDate()))
                .filter(busTicket -> !dateInFuture(busTicket.getStartDate()))
                .toList();
    }

    public List<BusTicket> validatePriceEven(List<BusTicket> list) {
        return list.stream()
                .filter(busTicket -> stringNotNullOrBlank(busTicket.getPrice()))
                .filter(busTicket -> priceEven(busTicket.getPrice()))
                .toList();
    }

    private boolean stringNotNullOrBlank(String price) {
        return price != null && !price.isBlank();
    }

    private boolean priceZeroNullOrBlank(String price) {
        return price == null || price.isBlank() ||
                price.equals("0");
    }

    private boolean dateInFuture(String stringStartDate) {
        LocalDate startDate = Mapper.mapStringToDate(stringStartDate);
        return startDate.isAfter(LocalDate.now());
    }

    private boolean priceEven(String price) {
        int priceInt = Mapper.mapStringToInt(price);
        return priceInt % 2 == 0;
    }
}
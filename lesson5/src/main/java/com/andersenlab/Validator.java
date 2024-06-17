package com.andersenlab;

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
                .filter(busTicket -> !stringNullOrBlank(busTicket.startDate))
                .filter(busTicket -> !dateNotInFuture(busTicket.getStartDate()))
                .toList();
    }

    public List<BusTicket> validatePriceEven(List<BusTicket> list) {
        return list.stream()
                .filter(busTicket -> !stringNullOrBlank(busTicket.price))
                .filter(busTicket -> priceEven(busTicket.price))
                .toList();
    }

    //Custom booleans
    private boolean stringNullOrBlank(String price) {
        return price == null || price.isBlank();
    }

    private boolean priceZeroNullOrBlank(String price) {
        return price == null || price.isBlank() ||
                price.equals("0");
    }

    private boolean dateNotInFuture(String stringStartDate) {
        LocalDate startdate = Mapper.mapStringToDate(stringStartDate);
        return startdate.isAfter(LocalDate.now());
    }

    private boolean priceEven(String price) {
        int priceInt = Mapper.mapStringToInt(price);
        return priceInt % 2 == 0;
    }
}
package com.andersenlab.lesson2.entity;

import com.andersenlab.lesson2.util.RandomTicketGenerator;
import com.andersenlab.lesson2.util.StadiumSector;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Ticket {
    private final LocalDateTime createdAt = LocalDateTime.now();
    private String id;
    private String concertHall;
    private String eventCode;
    private long time;
    private boolean isPromo;
    private StadiumSector stadiumSector;
    private double maxBackpackWeight;
    private BigDecimal decimalPriceInCents;

    private Ticket(String id, String concertHall, String eventCode,
                   long time, boolean isPromo,
                   StadiumSector stadiumSector,
                   double maxBackpackWeight, int intPriceInCents) {
        this.id = id;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.decimalPriceInCents = new BigDecimal(intPriceInCents);
    }

    private Ticket(String concertHall, String eventCode, long time) {
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
    }

    private Ticket() {
    }

    public static Ticket createTicket(String id, String concertHall, String eventCode,
                                      long time, boolean isPromo,
                                      StadiumSector stadiumSector,
                                      double maxBackpackWeight, int intPriceInCents) {
        validateId(id);
        validateConcertHall(concertHall);
        validateEventCode(eventCode);
        validateEventTime(time);
        return new Ticket(id,
                concertHall,
                eventCode,
                time,
                isPromo,
                stadiumSector,
                maxBackpackWeight,
                intPriceInCents);
    }

    public static Ticket createTicket(String concertHall, String eventCode, long time) {
        validateConcertHall(concertHall);
        validateEventCode(eventCode);
        validateEventTime(time);
        return new Ticket(concertHall,
                eventCode,
                time);
    }

    public static Ticket createTicket() {
        return new Ticket();
    }

    public static Ticket createRandomTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(RandomTicketGenerator.generateRandomID());
        return ticket;
    }

    private static void validateId(String id) {
        if (id.length() > 4) {
            throw new IllegalArgumentException("Ticket's id must contains maximum 4 characters");
        }
    }

    private static void validateConcertHall(String concertHall) {
        if (concertHall.length() > 10) {
            throw new IllegalArgumentException("ConcertHall must contains maximum 10 characters");
        }
    }

    private static void validateEventCode(String eventCode) {
        if (eventCode.length() != 3)
            throw new IllegalArgumentException("Ticket event code must contains 3 digits");
        char[] eventCodeChars = eventCode.toCharArray();
        for (char ch : eventCodeChars) {
            if (!Character.isDigit(ch)) {
                throw new IllegalArgumentException("Ticket event code must contains only digits");
            }
        }
    }

    private static void validateEventTime(long time) {
        if (System.currentTimeMillis() / 1000 > time) {
            throw new IllegalArgumentException("Ticket event time must be more than or equal to the current time");
        }
    }

    private String mapToDate(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
        return dateTime.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", time=" + mapToDate(time) +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeight=" + maxBackpackWeight +
                ", createdAt=" + createdAt +
                ", decimalPriceInCents=" + decimalPriceInCents +
                '}';
    }
}

package com.andersenlab.lesson2.entity;

import com.andersenlab.lesson2.abstraction.IdAbler;
import com.andersenlab.lesson2.abstraction.Printable;
import com.andersenlab.lesson2.abstraction.Shareable;
import com.andersenlab.lesson2.abstraction.annotation.NullChecker;
import com.andersenlab.lesson2.abstraction.annotation.NullableWarning;
import com.andersenlab.lesson2.util.StadiumSector;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class Ticket extends IdAbler implements Shareable, Printable {
    private final LocalDateTime createdAt = LocalDateTime.now();
    @NullableWarning
    private String id;
    private String concertHall;
    private String eventCode;
    private long time;
    private boolean isPromo;
    @NullableWarning
    private StadiumSector stadiumSector;
    private double maxBackpackWeight;
    @NullableWarning
    private BigDecimal decimalPriceInCents;

    // Constructors
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

    // Creators
    public static Ticket createTicket(String id, String concertHall, String eventCode,
                                      long time, boolean isPromo,
                                      StadiumSector stadiumSector,
                                      double maxBackpackWeight, int intPriceInCents) {
        validateId(id);
        validateConcertHall(concertHall);
        validateEventCode(eventCode);
        validateEventTime(time);
        Ticket ticket = new Ticket(id,
                concertHall,
                eventCode,
                time,
                isPromo,
                stadiumSector,
                maxBackpackWeight,
                intPriceInCents);
        NullChecker.checkObject(ticket);
        return ticket;
    }

    public static Ticket createTicket(String concertHall, String eventCode, long time) {
        validateConcertHall(concertHall);
        validateEventCode(eventCode);
        validateEventTime(time);
        Ticket ticket = new Ticket(concertHall,
                eventCode,
                time);
        NullChecker.checkObject(ticket);
        return ticket;
    }

    public static Ticket createTicket() {
        return new Ticket();
    }

    // Validators and mappers
    private static void validateId(String id) {
        if (id != null && id.length() > 4) {
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
            throw new IllegalArgumentException("Ticket event" +
                    " time must be more than or equal to the current time");
        }
    }

    private String mapToDate(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime dateTime = LocalDateTime
                .ofInstant(instant, ZoneId.of("UTC"));
        return dateTime.toString();
    }

    // Getters and Setters
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public String getConcertHall() {
        return concertHall;
    }

    public String getEventCode() {
        return eventCode;
    }

    public long getTime() {
        return time;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public StadiumSector getStadiumSector() {
        return stadiumSector;
    }

    public double getMaxBackpackWeight() {
        return maxBackpackWeight;
    }

    public BigDecimal getDecimalPriceInCents() {
        return decimalPriceInCents;
    }

    public void setStadiumSector(StadiumSector stadiumSector) {
        this.stadiumSector = stadiumSector;
    }

    public void setTime(long time) {
        this.time = time;
    }

    // Overrides
    @Override
    public void print() {
        System.out.println("Ticket ID: " + id);
    }

    @Override
    public void share(String phoneNumber) {
        System.out.printf("Share ticket with ID {%s} by phone {%s}.\n", id, phoneNumber);
    }

    @Override
    public void share(String phoneNumber, String email) {
        System.out.printf("Share ticket with ID {%s} by phone {%s}" +
                " and by email {%s}.\n", id, phoneNumber, email);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return time == ticket.time && isPromo == ticket.isPromo &&
                Double.compare(maxBackpackWeight, ticket.maxBackpackWeight) == 0 &&
                Objects.equals(createdAt, ticket.createdAt) &&
                Objects.equals(id, ticket.id) &&
                Objects.equals(concertHall, ticket.concertHall) &&
                Objects.equals(eventCode, ticket.eventCode) &&
                stadiumSector == ticket.stadiumSector &&
                Objects.equals(decimalPriceInCents, ticket.decimalPriceInCents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, id, concertHall,
                eventCode, time, isPromo, stadiumSector,
                maxBackpackWeight, decimalPriceInCents);
    }
}

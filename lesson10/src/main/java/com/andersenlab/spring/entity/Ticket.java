package com.andersenlab.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @Column(name = "ticket_type")
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;
    @Column(name = "creation_date")
    @CreationTimestamp
    private Timestamp creationDate;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", ticketType=" + ticketType +
                ", creationDate=" + creationDate +
                '}';
    }

    public enum TicketType {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }
}

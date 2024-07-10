package com.andersenlab.dao;

import com.andersenlab.dao.impl.TicketCRUDable;
import com.andersenlab.entity.Ticket;
import com.andersenlab.entity.TicketType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO extends ConnectionProviderDAO implements TicketCRUDable {

    @Override
    public boolean saveTicket(Ticket ticket) {
        String insertQuery = "INSERT INTO tickets (user_id, ticket_type, creation_date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, ticket.getUserId());
            preparedStatement.setObject(2, ticket.getTicketType(), Types.OTHER);
            preparedStatement.setTimestamp(3, ticket.getCreationDate());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Ticket getTicketById(int id) {
        String selectQuery = "SELECT * FROM tickets WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt("id"));
                ticket.setUserId(resultSet.getInt("user_id"));
                ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type")));
                ticket.setCreationDate(resultSet.getTimestamp("creation_date"));
                return ticket;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalArgumentException("No such ticket");
    }

    @Override
    public List<Ticket> getTicketsByUserId(int userId) {
        String selectQuery = "SELECT * FROM tickets WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt("id"));
                ticket.setUserId(resultSet.getInt("user_id"));
                ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type")));
                ticket.setCreationDate(resultSet.getTimestamp("creation_date"));
                tickets.add(ticket);
            }
            if (!tickets.isEmpty()) return tickets;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalArgumentException("No tickets by user id " + userId);
    }

    @Override
    public Ticket updateTicketTypeByTicketId(int ticketId, TicketType ticketType) {
        String updateQuery = "UPDATE tickets SET ticket_type = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setObject(1, ticketType, Types.OTHER);
            preparedStatement.setInt(2, ticketId);
            preparedStatement.execute();
            return getTicketById(ticketId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalArgumentException("No such ticket");
    }

    @Override
    public void deleteTicketById(int id) {
        getTicketById(id);
        String deleteQuery = "DELETE FROM tickets WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.printf("Ticket with id {%s} deleted.\n", id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

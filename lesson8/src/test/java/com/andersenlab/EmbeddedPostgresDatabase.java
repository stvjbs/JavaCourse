package com.andersenlab;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmbeddedPostgresDatabase {
    private final DataSource dataSource;
    private final Connection connection;

    public EmbeddedPostgresDatabase() {
        try {
            this.dataSource = createEmbeddedPostgres();
            setupDatabase();
            connection = dataSource.getConnection();
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Error initializing EmbeddedPostgresDatabase", e);
        }
    }

    private DataSource createEmbeddedPostgres() throws SQLException, IOException {
        return EmbeddedPostgres.builder().start().getPostgresDatabase();
    }

    private void setupDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            String createTableQuery = """
                    CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');
                    CREATE TABLE users (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL,
                                          creation_date TIMESTAMP NOT NULL
                    );
                    CREATE TABLE tickets (
                                            id SERIAL PRIMARY KEY,
                                            user_id INT NOT NULL,
                                            ticket_type ticket_type NOT NULL,
                                            creation_date TIMESTAMP NOT NULL,
                                            FOREIGN KEY (user_id) REFERENCES users(id)
                    );""";
            try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error setting up database", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection", e);
        }
    }
}

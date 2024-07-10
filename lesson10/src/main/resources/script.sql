SET search_path TO public;

CREATE TYPE TicketType AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');
CREATE TABLE users (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      creation_date TIMESTAMP NOT NULL
);
CREATE TABLE tickets (
                        id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL,
                        ticket_type TicketType NOT NULL,
                        creation_date TIMESTAMP NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE CAST (varchar AS TicketType) WITH INOUT AS IMPLICIT;
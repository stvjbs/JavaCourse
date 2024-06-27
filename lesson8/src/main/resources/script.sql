SET search_path TO public;

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
);
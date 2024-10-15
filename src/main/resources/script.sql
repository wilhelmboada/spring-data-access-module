CREATE TABLE IF NOT EXISTS users (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS events (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    ticket_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS tickets (
     id BIGSERIAL PRIMARY KEY,
     event_id BIGINT NOT NULL,
     user_id BIGINT NOT NULL,
     category BIGINT NOT NULL,
     place INT NOT NULL,
     CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES events (id),
     CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS user_accounts (
   id BIGSERIAL PRIMARY KEY,
   user_id BIGINT NOT NULL,
   prepaid_amount DECIMAL(10, 2) NOT NULL,
   CONSTRAINT fk_user_account FOREIGN KEY (user_id) REFERENCES users (id)
);
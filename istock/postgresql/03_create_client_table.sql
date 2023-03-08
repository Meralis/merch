DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS client;

DROP SEQUENCE IF EXISTS client_id_seq;

CREATE SEQUENCE client_id_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE client
(
    client_id  INTEGER DEFAULT nextval('client_id_seq') NOT NULL,
    status     VARCHAR(10),
    first_name VARCHAR(20),
    last_name  VARCHAR(20),
    email      VARCHAR(100),
    phone      VARCHAR(100),
    CONSTRAINT pk_client PRIMARY KEY (client_id)
);

INSERT INTO client (status, first_name, last_name, email, phone)
VALUES ('ACTIVE', 'Lucy', 'Klim', 'mail@gmail.com', '322223');
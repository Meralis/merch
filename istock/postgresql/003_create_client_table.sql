DROP TABLE IF EXISTS client;

DROP SEQUENCE client_id_seq;

CREATE SEQUENCE client_id_seq;

CREATE TABLE client (
  client_id INTEGER DEFAULT nextval('client_id_seq') NOT NULL,
  status VARCHAR(10),
  first_name VARCHAR(20),
  last_name VARCHAR(20),
  email VARCHAR(100),
  phone VARCHAR(100),
  CONSTRAINT pk_client PRIMARY KEY (client_id)
);
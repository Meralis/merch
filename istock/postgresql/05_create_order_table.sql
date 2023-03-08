DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS orders;

DROP SEQUENCE IF EXISTS order_id_seq;

CREATE SEQUENCE order_id_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE orders (
  order_id INTEGER DEFAULT nextval('order_id_seq') NOT NULL,
  status VARCHAR(10) NOT NULL,
  created TIMESTAMPTZ,
  client_id INTEGER NOT NULL,
  total INTEGER,
  delivery_address VARCHAR NOT NULL,
  CONSTRAINT pk_order PRIMARY KEY (order_id),
  CONSTRAINT fk_order_client FOREIGN KEY(client_id) REFERENCES client(client_id)
);
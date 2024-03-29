DROP TABLE IF EXISTS order_item;

DROP SEQUENCE IF EXISTS order_item_id_seq;

CREATE SEQUENCE order_item_id_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE order_item (
  order_item_id INTEGER DEFAULT nextval('order_item_id_seq') NOT NULL,
  product_id INTEGER NOT NULL,
  order_id INTEGER NOT NULL,
  quantity INTEGER NOT NULL,
  amount INTEGER,
  CONSTRAINT pk_order_item PRIMARY KEY (order_item_id),
  CONSTRAINT fk_order_item_product FOREIGN KEY(product_id) REFERENCES product(product_id),
  CONSTRAINT fk_order_item_order FOREIGN KEY(order_id) REFERENCES orders(order_id)
);
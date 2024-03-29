DROP TABLE IF EXISTS product cascade;

CREATE SEQUENCE product_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE product
(
    product_id integer NOT NULL DEFAULT nextval('product_seq'),
    description text,
    image_link varchar(255),
    price integer,
    title varchar(255),
    category varchar(255),
    CONSTRAINT products_pkey PRIMARY KEY (product_id)
)

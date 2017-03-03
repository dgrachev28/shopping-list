drop table PRODUCT;
drop table PRODUCT_DEFINITION;
drop table CATEGORY;
CREATE TABLE PRODUCT (id integer PRIMARY KEY, product_definition integer NOT NULL, quantity integer, quantity_unit text, price real, price_unit text, state integer NOT NULL);
CREATE TABLE PRODUCT_DEFINITION (id integer PRIMARY KEY, category text NOT NULL, name text NOT NULL);
CREATE TABLE CATEGORY (id integer PRIMARY KEY, name text NOT NULL UNIQUE);
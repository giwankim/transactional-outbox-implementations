CREATE TABLE orders
(
    id             BIGINT         NOT NULL AUTO_INCREMENT,
    amount         DECIMAL(19, 2) NOT NULL,
    customer_email VARCHAR(255)   NOT NULL,
    CONSTRAINT orders_pk PRIMARY KEY (id)
) ENGINE = InnoDB;

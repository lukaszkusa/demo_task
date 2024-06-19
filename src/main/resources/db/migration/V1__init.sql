CREATE TABLE product
(
    id                  UUID                            NOT NULL PRIMARY KEY,
    created_at          TIMESTAMP WITH TIME ZONE        NOT NULL DEFAULT NOW(),
    last_modified_at    TIMESTAMP WITH TIME ZONE        NOT NULL DEFAULT NOW(),
    product_name        VARCHAR(50)                     NOT NULL,
    price               NUMERIC                         NOT NULL

);
CREATE TABLE products_data
(
    id          UUID           NOT NULL,
    product_id  UUID           NOT NULL,
    name        VARCHAR(255)   NOT NULL,
    price       DECIMAL(14, 2) NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    deleted_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_products_data PRIMARY KEY (id)
);

ALTER TABLE products
    ADD product_data_id UUID;

ALTER TABLE products_data
    ADD CONSTRAINT FK_PRODUCTS_DATA_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

INSERT INTO products_data (id, product_id, name, price, created_at, deleted_at)
SELECT id, id, name, price, created_at, deleted_at
FROM products;

UPDATE products
SET product_data_id = id;

ALTER TABLE products
    DROP COLUMN created_at;

ALTER TABLE products
    DROP COLUMN deleted_at;

ALTER TABLE products
    DROP COLUMN name;

ALTER TABLE products
    DROP COLUMN price;
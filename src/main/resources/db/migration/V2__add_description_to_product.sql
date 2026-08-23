ALTER TABLE products
    ADD description VARCHAR(255);

ALTER TABLE products
DROP
COLUMN price;

ALTER TABLE products
    ADD price DECIMAL(14, 2) NOT NULL;
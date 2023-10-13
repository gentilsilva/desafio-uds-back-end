CREATE TABLE tb_ordered_items(
    id UUID DEFAULT RANDOM_UUID() NOT NULL UNIQUE,
    amount INTEGER NOT NULL,
    unitary_price DECIMAL(10, 2) NOT NULL,
    discount_percentage DOUBLE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    product_code VARCHAR(255) NOT NULL,
    order_number INTEGER NOT NULL UNIQUE,

    PRIMARY KEY(id),
    FOREIGN KEY(product_code) REFERENCES tb_products(code),
    FOREIGN KEY(order_number) REFERENCES tb_orders(number)
)
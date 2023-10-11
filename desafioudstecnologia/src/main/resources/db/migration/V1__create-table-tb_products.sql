CREATE TABLE tb_products(
    id UUID DEFAULT RANDOM_UUID() NOT NULL,
    code VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL UNIQUE,
    unitary_price DECIMAL(10, 2)
)
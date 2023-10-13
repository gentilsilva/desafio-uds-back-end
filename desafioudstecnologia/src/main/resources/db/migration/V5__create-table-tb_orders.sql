CREATE TABLE tb_orders(
    id UUID DEFAULT RANDOM_UUID() NOT NULL UNIQUE,
    number INTEGER NOT NULL UNIQUE,
    emission DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    client_cpf VARCHAR(11) NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(client_cpf) REFERENCES tb_clients(cpf)
)
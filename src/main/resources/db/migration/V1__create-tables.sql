CREATE TABLE IF NOT EXISTS sacola (
    id uuid NOT NULL,
    forma_pagamento varchar(255) NULL,
    sacola_fechada bool NOT NULL,
    valor_total_sacola float8 NOT NULL,
    cliente_id uuid NULL,
    CONSTRAINT sacola_pkey PRIMARY KEY (id),
    CONSTRAINT fkey_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS cliente (
    id uuid NOT NULL,
    cep varchar(255) NULL,
    logradouro varchar(255) NULL,
    nome varchar(255) NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS produto (
    id uuid NOT NULL,
    disponivel bool NOT NULL,
    nome varchar(255) NULL,
    valor_unitario float8 NOT NULL,
    restaurante_id uuid NULL,
    CONSTRAINT produto_pkey PRIMARY KEY (id),
    CONSTRAINT fkey_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
);

CREATE TABLE IF NOT EXISTS restaurante (
    id uuid NOT NULL,
    cep varchar(255) NULL,
    logradouro varchar(255) NULL,
    nome varchar(255) NULL,
    CONSTRAINT restaurante_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS restaurante_cardapio (
    restaurante_id uuid NOT NULL,
    cardapio_id uuid NOT NULL,
    CONSTRAINT fkey_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
    CONSTRAINT fkey_cardapio FOREIGN KEY (cardapio_id) REFERENCES produto(id)
);

CREATE TABLE IF NOT EXISTS sacola_itens (
    sacola_id uuid NOT NULL,
    itens_id uuid NOT NULL,
    CONSTRAINT fkey_sacola FOREIGN KEY (sacola_id) REFERENCES sacola(id),
    CONSTRAINT fkey_itens FOREIGN KEY (itens_id) REFERENCES item(id)
);



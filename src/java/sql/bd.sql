CREATE TABLE pedido
(
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pedido INTEGER NOT NULL,
    dono VARCHAR(20) NOT NULL,
    valor DOUBLE (20) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    atualizacao DATE NOT NULL  
);
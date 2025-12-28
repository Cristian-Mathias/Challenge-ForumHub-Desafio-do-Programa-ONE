CREATE TABLE topicos (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    estado_topico VARCHAR(50),
    autor VARCHAR(100),
    curso VARCHAR(100)
);
CREATE TABLE respostas (
    id BIGSERIAL PRIMARY KEY,
    mensagem TEXT NOT NULL,

    data_criacao TIMESTAMP NOT NULL,
    solucao BOOLEAN NOT NULL DEFAULT FALSE,

    topico_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,

    CONSTRAINT fk_resposta_topico
        FOREIGN KEY (topico_id)
        REFERENCES topicos(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_resposta_autor
        FOREIGN KEY (autor_id)
        REFERENCES usuarios(id)
);

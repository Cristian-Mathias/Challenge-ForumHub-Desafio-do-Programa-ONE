ALTER TABLE topicos ADD COLUMN usuario_id BIGINT;
ALTER TABLE topicos ALTER COLUMN usuario_id SET NOT NULL;
ALTER TABLE topicos
ADD CONSTRAINT fk_topicos_usuario
FOREIGN KEY (usuario_id) REFERENCES usuarios(id);
ALTER TABLE topicos DROP COLUMN autor;

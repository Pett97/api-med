ALTER TABLE consultas
ADD ativo TINYINT NOT NULL DEFAULT 1,
ADD motivo_cancelamento VARCHAR(50);

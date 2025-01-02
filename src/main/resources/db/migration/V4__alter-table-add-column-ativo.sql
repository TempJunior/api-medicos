ALTER TABLE tb_medicos ADD COLUMN ativo TINYINT DEFAULT 1;
UPDATE tb_medicos SET ativo = 1;

CREATE TABLE tb_medicos(

    id bigint NOT NULL auto_increment,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    crm VARCHAR(6) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    logradouro VARCHAR (100) NOT NULL,
    bairro VARCHAR (100) NOT NULL,
    cep VARCHAR (9) NOT NULL,
    complemento VARCHAR (100),
    numero VARCHAR (20),
    uf char(2)NOT NULL,
    cidade VARCHAR (100) NOT NULL,

    CONSTRAINT pk_medicos PRIMARY KEY (id)
);
CREATE TABLE public.pacientes (
	id serial NOT NULL,
	nome varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
    cpf varchar(14) NOT NULL,
    logradouro varchar(100) NOT NULL,
    bairro varchar(100) NOT NULL,
    cep varchar(9) NOT NULL,
    complemento varchar(100),
    numero varchar(20),
    uf varchar(2) NOT NULL,
    cidade varchar(100) NOT NULL,
	CONSTRAINT pacientes_pk PRIMARY KEY (id),
	CONSTRAINT pacientes_email_unique UNIQUE (email),
	CONSTRAINT pacientes_cpf_unique UNIQUE (cpf)
);

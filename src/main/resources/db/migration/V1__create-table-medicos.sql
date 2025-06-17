CREATE TABLE public.medicos (
	id serial NOT NULL,
	nome varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
    crm varchar(6) NOT NULL,
    especialidade varchar(100) NOT NULL,
    logradouro varchar(100) NOT NULL,
    bairro varchar(100) NOT NULL,
    cep varchar(9) NOT NULL,
    complemento varchar(100),
    numero varchar(20),
    uf varchar(2) NOT NULL,
    cidade varchar(100) NOT NULL,
	CONSTRAINT newtable_pk PRIMARY KEY (id),
	CONSTRAINT medicos_email_unique UNIQUE (email),
	CONSTRAINT medicos_crm_unique UNIQUE (crm)
);

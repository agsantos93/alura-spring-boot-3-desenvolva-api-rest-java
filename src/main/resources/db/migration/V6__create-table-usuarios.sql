CREATE TABLE public.usuarios (
	id serial NOT NULL,
	login varchar(100) NOT NULL,
	senha varchar(255) NOT NULL,
	CONSTRAINT usuarios_pk PRIMARY KEY (id)
);

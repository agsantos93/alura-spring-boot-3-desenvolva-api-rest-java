ALTER TABLE public.consultas ADD active BOOLEAN NOT NULL DEFAULT TRUE;
ALTER TABLE public.consultas ADD motivoCancelamento varchar(512);

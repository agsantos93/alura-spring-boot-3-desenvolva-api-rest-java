CREATE TABLE public.consultas (
	id serial NOT NULL,
	medico_id bigint NOT NULL,
	paciente_id bigint NOT NULL,
	data timestamp NOT NULL,
	CONSTRAINT consultas_pk PRIMARY KEY (id),
	CONSTRAINT consultas_medico_id_fk FOREIGN KEY (medico_id) REFERENCES public.medicos(id),
	CONSTRAINT consultas_paciente_id_fk FOREIGN KEY (paciente_id) REFERENCES public.pacientes(id)
);

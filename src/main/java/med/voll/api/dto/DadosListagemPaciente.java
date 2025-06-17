package med.voll.api.dto;

import med.voll.api.jpa.Paciente;

public record DadosListagemPaciente (
    Long id,
    String nome,
    String email
) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail());
    }

}

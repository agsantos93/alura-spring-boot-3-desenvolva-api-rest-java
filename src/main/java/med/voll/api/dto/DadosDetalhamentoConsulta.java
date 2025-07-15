package med.voll.api.dto;

import java.time.LocalDateTime;

import med.voll.api.jpa.Consulta;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data, boolean active, Motivo motivoCancelamento) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), consulta.getActive(), consulta.getMotivoCancelamento());
    }

}

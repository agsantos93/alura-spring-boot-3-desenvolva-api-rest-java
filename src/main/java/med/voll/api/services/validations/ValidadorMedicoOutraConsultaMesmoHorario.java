package med.voll.api.services.validations;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.repositorios.ConsultaRepository;

@Component
public class ValidadorMedicoOutraConsultaMesmoHorario implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();

        boolean medicoConsultaOutroHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dataConsulta);

        if (medicoConsultaOutroHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada este horário.");
        }
    }
}

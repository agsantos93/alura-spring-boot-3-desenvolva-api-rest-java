package med.voll.api.services.validations;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.repositorios.ConsultaRepository;

@Component
public class ValidadorPacienteSemOutraConsultaMesmaData implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();
        LocalDateTime primeiroHorario = dataConsulta.withHour(7);
        LocalDateTime ultimoHorario = dataConsulta.withHour(18);

        boolean pacientePossuiOutraConsultaMesmoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConsultaMesmoDia) {
            throw new ValidacaoException("Paciente já possui outra consulta agendada nesse dia.");
        }
    }
}

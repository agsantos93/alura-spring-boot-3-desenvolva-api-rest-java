package med.voll.api.services.validations;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.dto.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();
        LocalDateTime dataAgora = LocalDateTime.now();

        var duracao = Math.abs(Duration.between(dataConsulta, dataAgora).toMinutes());

        if (duracao < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}

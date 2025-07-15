package med.voll.api.services.validations;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.dto.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();

        boolean isDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpen = dataConsulta.getHour() < 7;
        boolean afterClose = dataConsulta.getHour() > 18;

        if (isDomingo || beforeOpen || afterClose) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica.");
        }
    }
}

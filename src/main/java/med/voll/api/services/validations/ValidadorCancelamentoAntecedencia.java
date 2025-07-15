package med.voll.api.services.validations;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.DadosCancelamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.jpa.Consulta;
import med.voll.api.repositorios.ConsultaRepository;

@Component
public class ValidadorCancelamentoAntecedencia implements ValidadorCancelamentoConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosCancelamentoConsulta dados) {
        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        if (LocalDateTime.now().isAfter(consulta.getData().minusHours(24))) {
            throw new ValidacaoException("Não é possível cancelar consulta com menos de 24h");
        }
    }
}

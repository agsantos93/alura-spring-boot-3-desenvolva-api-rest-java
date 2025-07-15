package med.voll.api.services.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.jpa.Medico;
import med.voll.api.repositorios.MedicoRepository;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null)
            return;

        Medico medico = repository.findActiveById(dados.idMedico());

        if (medico == null) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído.");
        }
    }
}

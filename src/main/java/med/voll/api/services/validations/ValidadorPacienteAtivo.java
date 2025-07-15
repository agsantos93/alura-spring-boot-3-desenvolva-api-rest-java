package med.voll.api.services.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.jpa.Paciente;
import med.voll.api.repositorios.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null)
            return;

        Paciente paciente = repository.findActiveById(dados.idPaciente());

        if (paciente == null) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído.");
        }
    }
}

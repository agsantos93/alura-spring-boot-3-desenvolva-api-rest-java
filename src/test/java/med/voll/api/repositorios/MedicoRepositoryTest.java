package med.voll.api.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import med.voll.api.dto.DadosCadastroMedico;
import med.voll.api.dto.DadosCadastroPaciente;
import med.voll.api.dto.DadosEndereco;
import med.voll.api.dto.Especialidade;
import med.voll.api.jpa.Consulta;
import med.voll.api.jpa.Medico;
import med.voll.api.jpa.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando único médico cadastrado não estiver disponível na data.")
    void testMedicoAleatorioLivreNaDataScenario1() {
        var proxSegDez = LocalDate.now()
                            .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                            .atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proxSegDez);

        var medicoLivre = repository.medicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proxSegDez);
        assertNull(medicoLivre);
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponível na data.")
    void testMedicoAleatorioLivreNaDataScenario2() {
        var proxSegDez = LocalDate.now()
                            .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                            .atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        var medicoLivre = repository.medicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proxSegDez);
        assertEquals(medicoLivre, medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                especialidade,
                dadosEndereco(),
                crm
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                null,
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null
        );
    }
}

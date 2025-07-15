package med.voll.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import med.voll.api.dto.DadosAgendamentoConsulta;
import med.voll.api.dto.DadosCadastroMedico;
import med.voll.api.dto.DadosDetalhamentoConsulta;
import med.voll.api.dto.DadosDetalhamentoMedico;
import med.voll.api.dto.DadosEndereco;
import med.voll.api.dto.Especialidade;
import med.voll.api.jpa.Endereco;
import med.voll.api.jpa.Medico;
import med.voll.api.repositorios.MedicoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private MedicoRepository repository;

    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJson;


    @Test
    @DisplayName("Deveria devolver codigo 400 quando informações estão inválidas")
    @WithMockUser
    void testCadastrar_scenario1() throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.post("/medicos")).andReturn().getResponse();
        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Deveria devolver codigo 201 quando informações estão válidas")
    @WithMockUser(roles = "ADMIN")
    void testCadastrar_scenario2() throws Exception {
        var dadosCadastro = new DadosCadastroMedico(
            "Medico",
            "medico@voll.med",
            "61999999999",
            Especialidade.CARDIOLOGIA,
            dadosEndereco(),
            "123456");
        when(repository.save(any())).thenReturn(new Medico(dadosCadastro));

        var response = mvc.perform(
                MockMvcRequestBuilders.post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroMedicoJson.write(new DadosCadastroMedico("Medico", "medico@voll.med", "61999999999", Especialidade.CARDIOLOGIA, dadosEndereco(), "123456")).getJson())
            ).andReturn().getResponse();
        var dadosDetalhamento = new DadosDetalhamentoMedico(
            null,
            dadosCadastro.nome(),
            dadosCadastro.email(),
            dadosCadastro.crm(),
            dadosCadastro.telefone(),
            dadosCadastro.especialidade(),
            new Endereco(dadosCadastro.endereco())
        );
        var jsonEsperado = dadosDetalhamentoMedicoJson.write(dadosDetalhamento).getJson();

        assertEquals(201, response.getStatus());
        assertEquals(jsonEsperado, response.getContentAsString());
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

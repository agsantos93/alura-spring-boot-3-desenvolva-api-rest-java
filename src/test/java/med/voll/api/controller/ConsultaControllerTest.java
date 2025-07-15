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
import med.voll.api.dto.DadosDetalhamentoConsulta;
import med.voll.api.dto.Especialidade;
import med.voll.api.services.AgendaConsultasService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoJson;

    @MockitoBean
    private AgendaConsultasService service;


    @Test
    @DisplayName("Deveria devolver codigo 400 quando informações estão inválidas")
    @WithMockUser
    void testAgendar_scenario1() throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.post("/consultas")).andReturn().getResponse();
        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Deveria devolver codigo 201 quando informações estão válidas")
    @WithMockUser
    void testAgendar_scenario2() throws Exception {
        LocalDateTime data = LocalDateTime.now().plusHours(2);
        Especialidade especialidade = Especialidade.CARDIOLOGIA;
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data, true, null);
        when(service.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mvc.perform(
                MockMvcRequestBuilders.post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosAgendamentoJson.write(new DadosAgendamentoConsulta(2l, 5l, data, especialidade)).getJson())
            ).andReturn().getResponse();

        System.out.println(response);

        var jsonEsperado = dadosDetalhamentoJson.write(dadosDetalhamento).getJson();
        assertEquals(201, response.getStatus());
        assertEquals(jsonEsperado, response.getContentAsString());
    }
}

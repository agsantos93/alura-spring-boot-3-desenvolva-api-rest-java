package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
    @NotNull
    Long idConsulta,
    @NotNull
    Motivo motivo
) {

}

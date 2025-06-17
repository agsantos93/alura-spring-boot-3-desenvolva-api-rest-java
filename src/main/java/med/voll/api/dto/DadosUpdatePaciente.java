package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosUpdatePaciente (
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco
) {
}

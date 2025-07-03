package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico (
    @NotBlank(message = "{nome.obrigatorio}")
    String nome,
    @NotBlank(message = "{email.obrigatorio}")
    @Email(message = "{email.invalido}")
    String email,
    @NotBlank(message = "{telefone.obrigatorio}")
    String telefone,
    @NotNull(message = "{especialidade.obrigatorio}")
    Especialidade especialidade,
    @NotNull(message = "{endereco.obrigatorio}")
    @Valid
    DadosEndereco endereco,
    @NotBlank(message = "{crm.obrigatorio}")
    @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
    String crm
) {
}

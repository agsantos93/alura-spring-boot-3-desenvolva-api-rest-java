package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
    @NotBlank(message = "{logradour.obrigatorio}")
    String logradouro,
    String numero,
    @NotBlank(message = "{bairro.obrigatorio}")
    String bairro,
    @NotBlank(message = "{cep.obrigatorio}")
    @Pattern(regexp = "\\d{8}", message = "{cep.invalido}")
    String cep,
    @NotBlank(message = "{cidade.obrigatorio}")
    String cidade,
    @NotBlank(message = "{uf.obrigatorio}")
    String uf,
    String complemento
) {
}

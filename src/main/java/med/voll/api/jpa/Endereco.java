package med.voll.api.jpa;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DadosEndereco;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.cep = dados.cep();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
    }

    public void update(DadosEndereco dados) {
        if (dados.logradouro() != null)
            this.logradouro = dados.logradouro();
        if (dados.numero() != null)
            this.numero = dados.numero();
        if (dados.complemento() != null)
            this.complemento = dados.complemento();
        if (dados.cep() != null)
            this.cep = dados.cep();
        if (dados.bairro() != null)
            this.bairro = dados.bairro();
        if (dados.cidade() != null)
            this.cidade = dados.cidade();
        if (dados.bairro() != null)
            this.uf = dados.uf();
    }

}

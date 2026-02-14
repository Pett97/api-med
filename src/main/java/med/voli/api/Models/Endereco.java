package med.voli.api.Models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voli.api.DadosEndereco.DadosEndereco;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
        private String logradouro;
        private String bairro;
        private String cep;
        private String cidade;
        private String uf;
        private String numero;

        public Endereco(DadosEndereco dados) {
                this.logradouro = dados.logradouro();
                this.bairro = dados.bairro();
                this.cep = dados.cep();
                this.cidade = dados.cidade();
                this.uf = dados.uF();
                this.numero = dados.numero();
        }
}

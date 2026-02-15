package med.voli.api.domain.Models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voli.api.domain.DadosEndereco.DadosEndereco;

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

        public void atualizarDados(DadosEndereco dados) {
                if (dados.logradouro() != null) {
                        this.logradouro = dados.logradouro();
                }
                if (dados.bairro() != null) {
                        this.bairro = dados.bairro();
                }
                if (dados.cep() != null) {
                        this.cep = dados.cep();
                }
                if (dados.cidade() != null) {
                        this.cidade = dados.cidade();
                }
                if (dados.uF() != null) {
                        this.uf = dados.uF();
                }
                if (dados.numero() != null) {
                        this.numero = dados.numero();
                }

        }
}

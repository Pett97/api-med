package med.voli.api.Medico;

import med.voli.api.DadosEndereco.DadosEndereco;
import med.voli.api.Enums.Especialidade;

public record DadosCadastroMedico(String nome,String email,String crm,Especialidade especialidade,DadosEndereco endereco) {

}

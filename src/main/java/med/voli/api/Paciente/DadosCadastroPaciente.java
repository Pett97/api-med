package med.voli.api.Paciente;

import med.voli.api.DadosEndereco.DadosEndereco;

public record DadosCadastroPaciente(String nome,String email,String telefone,String cpf,DadosEndereco endereco) {

}

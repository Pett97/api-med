package med.voli.api.Paciente;

import jakarta.validation.constraints.NotNull;
import med.voli.api.DadosEndereco.DadosEndereco;

public record DadosAtualizarPaciente(
      @NotNull Long id,

      String nome,

      String telefone,

      DadosEndereco endereco) {

}

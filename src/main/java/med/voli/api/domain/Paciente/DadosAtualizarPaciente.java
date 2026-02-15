package med.voli.api.domain.Paciente;

import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.DadosEndereco.DadosEndereco;

public record DadosAtualizarPaciente(
      @NotNull Long id,

      String nome,

      String telefone,

      DadosEndereco endereco) {

}

package med.voli.api.domain.Medico;
import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.DadosEndereco.DadosEndereco;

public record DadosAtualizarMedico(

      @NotNull
      Long id,

      String nome,

      String telefone,

      DadosEndereco endereco) {

}

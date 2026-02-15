package med.voli.api.Medico;
import jakarta.validation.constraints.NotNull;
import med.voli.api.DadosEndereco.DadosEndereco;

public record DadosAtualizarMedico(

      @NotNull
      Long id,

      String nome,

      String telefone,

      DadosEndereco endereco) {

}

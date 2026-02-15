package med.voli.api.domain.Paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voli.api.domain.DadosEndereco.DadosEndereco;

public record DadosCadastroPaciente(

      @NotBlank String nome,

      @NotBlank @Email String email,

      @NotBlank @Pattern(regexp = "\\d{11}") String telefone,

      @NotBlank String cpf,

      @NotNull @Valid DadosEndereco endereco) {

}
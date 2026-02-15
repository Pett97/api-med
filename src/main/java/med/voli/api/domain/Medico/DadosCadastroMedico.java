package med.voli.api.domain.Medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voli.api.domain.DadosEndereco.DadosEndereco;
import med.voli.api.domain.Enums.Especialidade;

public record DadosCadastroMedico(

   @NotBlank String nome,

   @NotBlank(message = "Email é obrigatório") @Email(message = "Formato do email é inválido") String email,

   @NotBlank String telefone,

   @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,

   @NotNull Especialidade especialidade,

   @NotNull @Valid DadosEndereco endereco) {}
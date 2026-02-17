package med.voli.api.domain.Usuario;

import jakarta.validation.constraints.NotNull;

public record DtoLogin(
   
   @NotNull
   String login,

   @NotNull
   String senha) {

}

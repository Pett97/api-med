package med.voli.api.domain.Consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.Enums.Especialidade;

public record DtoAgendamentoConsulta(
      Long idMedico,

      @NotNull Long idPaciente,

      @NotNull @Future LocalDateTime data,

       Especialidade especialidade

) {
}

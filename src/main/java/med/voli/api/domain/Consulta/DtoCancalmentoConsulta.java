package med.voli.api.domain.Consulta;
import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.Enums.MotivoCancelamento;

public record DtoCancalmentoConsulta(

      @NotNull Long idConsulta,

      @NotNull MotivoCancelamento motivoCancelamento
) {
}

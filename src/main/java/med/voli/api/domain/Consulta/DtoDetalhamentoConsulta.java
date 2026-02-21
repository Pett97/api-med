package med.voli.api.domain.Consulta;

import java.time.LocalDateTime;

public record DtoDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

   public DtoDetalhamentoConsulta(Consulta consulta) {
      this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
   }
}
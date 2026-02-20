package med.voli.api.domain.Consulta;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRespository extends JpaRepository<Consulta, Long> {

   boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

   boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime dataConsulta);
}

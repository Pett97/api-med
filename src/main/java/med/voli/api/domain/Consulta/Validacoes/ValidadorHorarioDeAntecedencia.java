package med.voli.api.domain.Consulta.Validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voli.api.domain.ValidacaoExecption;
import med.voli.api.domain.Consulta.DtoAgendamentoConsulta;

@Component
public class ValidadorHorarioDeAntecedencia implements ValidadorAgendamentoDeConsultas {
   public void validar(DtoAgendamentoConsulta dados) {
      var dataConsulta = dados.data();
      var dataAgora = LocalDateTime.now();
      var diferencaEmMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();

      if (diferencaEmMinutos < 30) {
         throw new ValidacaoExecption("Consulta dever ser agendada com antecedencia de 30 minutos");
      }
   }
}

package med.voli.api.domain.Consulta.Validacoes;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voli.api.domain.ValidacaoExecption;
import med.voli.api.domain.Consulta.DtoAgendamentoConsulta;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas {

   public void validar(DtoAgendamentoConsulta dados) {
      var dataConsulta = dados.data();

      var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
      var antesDaHoraDeAberturaDaClinica = dataConsulta.getHour() < 7;
      var depoisDaHoraDeEncerramentoDaClinica = dataConsulta.getHour() > 18;
      if (domingo || antesDaHoraDeAberturaDaClinica || depoisDaHoraDeEncerramentoDaClinica) {
         throw new ValidacaoExecption("Consulta Fora do Horario de funcionamneto");
      }
   }
}

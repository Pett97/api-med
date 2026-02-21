package med.voli.api.domain.Consulta.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voli.api.domain.ValidacaoExecption;
import med.voli.api.domain.Consulta.ConsultaRespository;
import med.voli.api.domain.Consulta.DtoAgendamentoConsulta;


@Component
public class ValidarPacienteSemOutraConsultaNoDia  implements ValidadorAgendamentoDeConsultas{

   @Autowired
   private ConsultaRespository consultaRespository;

   public void validar(DtoAgendamentoConsulta dados) {
      var primeiroHorario = dados.data().withHour(7);
      var ultimoHorario = dados.data().withHour(18);
      var pacientePossuiOutraConsultaNodia = consultaRespository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario, ultimoHorario);
      
      if (pacientePossuiOutraConsultaNodia) {
         throw new ValidacaoExecption("Paciente ja possui uma consulta agendada nesse dia");
      }

   }
}

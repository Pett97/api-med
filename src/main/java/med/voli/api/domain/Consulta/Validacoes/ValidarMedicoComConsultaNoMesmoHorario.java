package med.voli.api.domain.Consulta.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voli.api.domain.ValidacaoExecption;
import med.voli.api.domain.Consulta.ConsultaRespository;
import med.voli.api.domain.Consulta.DtoAgendamentoConsulta;

@Component
public class ValidarMedicoComConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas {

   @Autowired
   private ConsultaRespository consultaRespository;

   public void validar(DtoAgendamentoConsulta dados){
      var medicoPossuiOutraConsultaNoMesmoHorario = consultaRespository.existsByMedicoIdAndData(dados.idMedico(),dados.data());
      if(medicoPossuiOutraConsultaNoMesmoHorario){
         throw new ValidacaoExecption("Medico ja possui uma consulta nesse horario");
      }
   }
}

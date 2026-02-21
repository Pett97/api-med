package med.voli.api.domain.Consulta.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voli.api.domain.ValidacaoExecption;
import med.voli.api.domain.Consulta.DtoAgendamentoConsulta;
import med.voli.api.domain.Paciente.PacienteRepository;

@Component
public class ValidarPacienteAtivo implements ValidadorAgendamentoDeConsultas {

   @Autowired
   private PacienteRepository pacienteRepository;

   public void validar(DtoAgendamentoConsulta dados) {

      var tenhoEssePaciente = pacienteRepository.existsById(dados.idPaciente());
      if (!tenhoEssePaciente) {
         throw new ValidacaoExecption("Não foi encontrado nenhum paciente com esse id");
      }
      var pacienteAtivo = pacienteRepository.existsByIdAndAtivoTrue(dados.idPaciente());

      if (!pacienteAtivo) {
         throw new ValidacaoExecption("Paciente precisa estar ativo");
      }
   }
}

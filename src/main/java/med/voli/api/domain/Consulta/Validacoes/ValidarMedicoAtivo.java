package med.voli.api.domain.Consulta.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voli.api.domain.ValidacaoExecption;
import med.voli.api.domain.Consulta.DtoAgendamentoConsulta;
import med.voli.api.domain.Medico.MedicoRepository;

@Component
public class ValidarMedicoAtivo implements ValidadorAgendamentoDeConsultas {

   @Autowired
   private MedicoRepository medicoRepository;

   public void validar(DtoAgendamentoConsulta dados) {
      if (dados.idMedico() == null) {
         return;
      }

      var medicoAtivo = medicoRepository.existsByIdAndAtivoTrue(dados.idMedico());
      if (!medicoAtivo) {
         throw new ValidacaoExecption("Consulta não pode ser agendada com medico inativo ou inexistente");
      }
   }
}

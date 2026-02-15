package med.voli.api.domain.Medico;

import med.voli.api.domain.Enums.Especialidade;

public record DadosListagemMedicos(Long id,String nome, String email, String crm, boolean ativo,Especialidade especialidade) {

   public DadosListagemMedicos(Medico medico) {
      this(medico.getId() ,medico.getNome(), medico.getEmail(), medico.getCrm(), medico.isAtivo() ,medico.getEspecialidade());
   }
}

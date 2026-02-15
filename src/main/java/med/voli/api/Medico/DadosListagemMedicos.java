package med.voli.api.Medico;

import med.voli.api.Enums.Especialidade;

public record DadosListagemMedicos(Long id,String nome, String email, String crm, boolean ativo,Especialidade especialidade) {

   public DadosListagemMedicos(Medico medico) {
      this(medico.getId() ,medico.getNome(), medico.getEmail(), medico.getCrm(), medico.isAtivo() ,medico.getEspecialidade());
   }
}

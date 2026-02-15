package med.voli.api.domain.Paciente;

public record DadosListagemPacientes(Long id,boolean ativo,String nome, String email, String telefone) {

   public DadosListagemPacientes(Paciente paciente) {
      this(paciente.getId(),paciente.isAtivo(),paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
   }
}

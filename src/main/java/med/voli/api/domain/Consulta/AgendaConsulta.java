package med.voli.api.domain.Consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voli.api.domain.ValidacaoExecption;
import med.voli.api.domain.Consulta.Validacoes.ValidadoCancelamento;
import med.voli.api.domain.Consulta.Validacoes.ValidadorAgendamentoDeConsultas;
import med.voli.api.domain.Medico.Medico;
import med.voli.api.domain.Medico.MedicoRepository;
import med.voli.api.domain.Paciente.PacienteRepository;

@Service
public class AgendaConsulta {

   @Autowired
   private ConsultaRespository consultaRespository;
   @Autowired
   private MedicoRepository medicoRepository;
   @Autowired
   private PacienteRepository pacienteRepository;

   @Autowired
   private List<ValidadorAgendamentoDeConsultas> validadores;

   @Autowired
   private List<ValidadoCancelamento> validadoresCancelamentoConsulta;

   public DtoDetalhamentoConsulta agendar(DtoAgendamentoConsulta dados) {
      validadores.forEach(validador -> validador.validar(dados));
      var medico = escolherMedico(dados);
      var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
      var consulta = new Consulta(null, medico, paciente, dados.data(), null);
      consultaRespository.save(consulta);

      return new DtoDetalhamentoConsulta(consulta);

   }

   private Medico escolherMedico(DtoAgendamentoConsulta dados) {
      if (dados.idMedico() != null) {
         var medico = medicoRepository.getReferenceById(dados.idMedico());
         return medico;
      }

      var medico = medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

      if (medico == null) {
         throw new ValidacaoExecption("Nenhum médico disponível");
      }
      return medico;
   }

   public void cancelar(DtoCancalmentoConsulta dados) {
      validadoresCancelamentoConsulta.forEach(validador -> validador.validar(dados));
      var consulta = consultaRespository.getReferenceById(dados.idConsulta());
      consulta.cancelar(dados.motivoCancelamento());
   }
}

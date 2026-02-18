package med.voli.api.domain.Consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voli.api.domain.ValidacaoExecption;
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

   public void agendar(DtoAgendamentoConsulta dados) {

      if (!pacienteRepository.existsById(dados.idPaciente())) {
         throw new ValidacaoExecption("ID do paciente informado não existe");
      }

      if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
         throw new ValidacaoExecption("ID do medico informado não existe");
      }

      var medico = escolherMedico(dados);
      var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
      var consulta = new Consulta(null, medico, paciente, dados.data(),null);
      consultaRespository.save(consulta);

   }

   private Medico escolherMedico(DtoAgendamentoConsulta dados) {
      if (dados.idMedico() != null) {
         return medicoRepository.getReferenceById(dados.idMedico());
      }

      if (dados.especialidade() == null) {
         throw new ValidacaoExecption("Especialidade é obrigatoria quando medico nã for escolhido");
      }

      return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
   }

   public void cancelar(DtoCancalmentoConsulta dados) {
      if(!consultaRespository.existsById(dados.idConsulta())){
          throw new ValidacaoExecption("Id da consulta informado não existe!");
      }

      var consulta = consultaRespository.getReferenceById(dados.idConsulta());
      consulta.cancelar(dados.motivoCancelamento());
   }
}

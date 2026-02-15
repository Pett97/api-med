package med.voli.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.Paciente.DadosAtualizarPaciente;
import med.voli.api.Paciente.DadosCadastroPaciente;
import med.voli.api.Paciente.DadosListagemPacientes;
import med.voli.api.Paciente.Paciente;
import med.voli.api.Paciente.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

      @Autowired
      private PacienteRepository pacienteRepository;

      @PostMapping
      @Transactional
      public void Cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
            pacienteRepository.save(new Paciente(dados));
      }

      @GetMapping
      public Page<DadosListagemPacientes> findAllByAtivoTrue(@PageableDefault(size = 5) Pageable paginacao) {
            return this.pacienteRepository.findAllByAtivoTrue(paginacao);
      }

      @PutMapping
      @Transactional
      public void atualizarDados(@RequestBody @Valid DadosAtualizarPaciente dados) {
            var paciente = pacienteRepository.getReferenceById(dados.id());
            paciente.atualizarDados(dados);

      }

      @DeleteMapping("/{id}")
      @Transactional
      public void deletar(@PathVariable long id){
            var paciente = pacienteRepository.getReferenceById(id);
            paciente.desativar();
      }
}

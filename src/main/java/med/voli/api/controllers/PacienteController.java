package med.voli.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.domain.Paciente.DadosAtualizarPaciente;
import med.voli.api.domain.Paciente.DadosCadastroPaciente;
import med.voli.api.domain.Paciente.DadosDetalhamentoPaciente;
import med.voli.api.domain.Paciente.DadosListagemPacientes;
import med.voli.api.domain.Paciente.Paciente;
import med.voli.api.domain.Paciente.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

      @Autowired
      private PacienteRepository pacienteRepository;

      @PostMapping
      @Transactional
      public ResponseEntity Cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uBuilder) {

            var paciente = new Paciente(dados);
            pacienteRepository.save(paciente);
            var uri = uBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

            return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
      }

      @GetMapping
      public ResponseEntity < Page < DadosListagemPacientes >> listar(@PageableDefault(size = 5) Pageable paginacao) {
            var page = this.pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPacientes::new);

            return ResponseEntity.ok(page);
      }

      @PutMapping
      @Transactional
      public ResponseEntity atualizarDados(@RequestBody @Valid DadosAtualizarPaciente dados) {
            var paciente = pacienteRepository.getReferenceById(dados.id());
            paciente.atualizarDados(dados);
            return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));

      }

      @DeleteMapping("/{id}")
      @Transactional
      public ResponseEntity deletar(@PathVariable long id) {
            var paciente = pacienteRepository.getReferenceById(id);
            paciente.desativar();

            return ResponseEntity.noContent().build();
      }
}
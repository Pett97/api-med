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
import med.voli.api.domain.Medico.DadosAtualizarMedico;
import med.voli.api.domain.Medico.DadosCadastroMedico;
import med.voli.api.domain.Medico.DadosDetalhamentoMedico;
import med.voli.api.domain.Medico.DadosListagemMedicos;
import med.voli.api.domain.Medico.Medico;
import med.voli.api.domain.Medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

   @Autowired
   private MedicoRepository medicoRepository;

   @PostMapping
   @Transactional
   public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
      var medico = new Medico(dados);
      medicoRepository.save(medico);

      var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

      return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
   }

   @GetMapping
   public ResponseEntity < Page < DadosListagemMedicos >> listar(@PageableDefault(size = 10) Pageable paginacao) {
      var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);
      return ResponseEntity.ok(page);
   }

   @GetMapping("/{id}")
   public ResponseEntity<DadosDetalhamentoMedico> dadosMedico(@PathVariable Long id) {
      var medico = medicoRepository.getReferenceById(id);
      return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
   }

   @PutMapping
   @Transactional
   public ResponseEntity<DadosDetalhamentoMedico> atualizarMedico(@RequestBody @Valid DadosAtualizarMedico dados) {
      var medico = medicoRepository.getReferenceById(dados.id());
      medico.atualizarDados(dados);

      return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
   }

   @DeleteMapping("/{id}")
   @Transactional
   public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
      var medico = medicoRepository.getReferenceById(id);
      medico.deletar();

      return ResponseEntity.noContent().build();
   }
}
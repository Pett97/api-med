package med.voli.api.controllers;

import java.util.List;
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
import med.voli.api.Medico.DadosAtualizarMedico;
import med.voli.api.Medico.DadosCadastroMedico;
import med.voli.api.Medico.DadosListagemMedicos;
import med.voli.api.Medico.Medico;
import med.voli.api.Medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

   @Autowired
   private MedicoRepository medicoRepository;

   @PostMapping
   @Transactional
   public void Cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
      System.out.println("teste");
      System.out.println(dados);
      medicoRepository.save(new Medico(dados));
   }

   @GetMapping
   public Page<DadosListagemMedicos> findAllByAtivoTrue(@PageableDefault(size = 10) Pageable paginacao) {
      return medicoRepository.findAllByAtivoTrue(paginacao);
   }

   @PutMapping
   @Transactional
   public void atualizarMedico(@RequestBody @Valid DadosAtualizarMedico dados) {
      var medico = medicoRepository.getReferenceById(dados.id());
      medico.atualizarDados(dados);
   }

   @DeleteMapping("/{id}")
   @Transactional
   public void deletarMedico(@PathVariable Long id){
      var medico = medicoRepository.getReferenceById(id);
      medico.deletar();
   }
}

package med.voli.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.Medico.DadosCadastroMedico;
import med.voli.api.Medico.Medico;
import med.voli.api.Medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {
   
   @Autowired
   private MedicoRepository medicoRepository;

   @PostMapping
   @Transactional
   
   public void Cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
      System.out.println("teste");
      System.out.println(dados);
      medicoRepository.save(new Medico(dados));
   }
}

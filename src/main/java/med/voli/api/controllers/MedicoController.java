package med.voli.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voli.api.Medico.DadosCadastroMedico;

@RestController
@RequestMapping("medicos")
public class MedicoController {
   
   @PostMapping
   public void Cadastrar(@RequestBody DadosCadastroMedico dados){
      System.out.println(dados);
   }
}

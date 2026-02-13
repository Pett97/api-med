package med.voli.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voli.api.Paciente.DadosCadastroPaciente;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
   

   @PostMapping
   public void Cadastrar(@RequestBody DadosCadastroPaciente dados){
      System.out.println(dados);
   }
}

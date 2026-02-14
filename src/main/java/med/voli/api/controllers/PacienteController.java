package med.voli.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voli.api.Paciente.DadosCadastroPaciente;
import med.voli.api.Paciente.Paciente;
import med.voli.api.Paciente.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
   
   @Autowired
   private PacienteRepository pacienteRepository;

   @PostMapping
   public void Cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
         pacienteRepository.save(new Paciente(dados));
   }
}

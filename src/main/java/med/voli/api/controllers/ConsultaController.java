package med.voli.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.domain.Consulta.AgendaConsulta;
import med.voli.api.domain.Consulta.DtoAgendamentoConsulta;
import med.voli.api.domain.Consulta.DtoCancalmentoConsulta;
import med.voli.api.domain.Consulta.DtoDetalhamentoConsulta;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

   @Autowired
   private AgendaConsulta agendaConsulta;

   @PostMapping
   @Transactional
   public ResponseEntity<DtoDetalhamentoConsulta> agendar(@RequestBody @Valid DtoAgendamentoConsulta dados) {
      System.out.println(dados);
      agendaConsulta.agendar(dados);
      return ResponseEntity
            .ok(new DtoDetalhamentoConsulta(dados.idMedico(), dados.idMedico(), dados.idPaciente(), dados.data()));
   }

   @DeleteMapping
   @Transactional
   public ResponseEntity<Void> cancelar(@RequestBody @Valid DtoCancalmentoConsulta dados) {
      agendaConsulta.cancelar(dados);
      return ResponseEntity.noContent().build();
   }
}

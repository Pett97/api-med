package med.voli.api.domain.Consulta.Validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voli.api.domain.ValidacaoExecption;
import med.voli.api.domain.Consulta.ConsultaRespository;
import med.voli.api.domain.Consulta.DtoCancalmentoConsulta;

@Component
public class ValidarCancelamentoComAntecedencia implements ValidadoCancelamento {

   @Autowired
   private ConsultaRespository consultaRespository;

   public void validar(DtoCancalmentoConsulta dados) {
      var consulta = consultaRespository.getReferenceById(dados.idConsulta());
      var datAtual = LocalDateTime.now();
      var dataConsulta = consulta.getData();

      var diferencaData = Duration.between(datAtual, dataConsulta).toHours();

      if (diferencaData < 24) {
         throw new ValidacaoExecption("O Cancelamento da Consulta tem prazo Minimo de 24 horas");
      }
   }

}

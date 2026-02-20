package med.voli.api.domain.Consulta.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voli.api.domain.ValidacaoExecption;
import med.voli.api.domain.Consulta.ConsultaRespository;
import med.voli.api.domain.Consulta.DtoCancalmentoConsulta;

@Component
public class ValidarCancelamentoConsulta implements ValidadoCancelamento {

   @Autowired
   private ConsultaRespository consultaRespository;

   public void validar(DtoCancalmentoConsulta dados) {
      var consulta = consultaRespository.existsById(dados.idConsulta());
      if(!consulta){
         throw new ValidacaoExecption("Id Consulta Inexistente");
      } 
   }
}

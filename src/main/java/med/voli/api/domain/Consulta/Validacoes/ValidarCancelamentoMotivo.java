package med.voli.api.domain.Consulta.Validacoes;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import med.voli.api.domain.ValidacaoExecption;
import med.voli.api.domain.Consulta.DtoCancalmentoConsulta;
import med.voli.api.domain.Enums.MotivoCancelamento;

@Component
public class ValidarCancelamentoMotivo implements ValidadoCancelamento {

   public void validar(DtoCancalmentoConsulta dados) {
      if (dados.motivoCancelamento() == null) {
         throw new ValidacaoExecption("é obrigátorio informar o motivo do cancelamento");
      }
   }
}

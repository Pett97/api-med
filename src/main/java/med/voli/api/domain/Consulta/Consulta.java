package med.voli.api.domain.Consulta;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voli.api.domain.Enums.MotivoCancelamento;
import med.voli.api.domain.Medico.Medico;
import med.voli.api.domain.Paciente.Paciente;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity(name = "Consulta")
@Table(name = "consultas")
public class Consulta {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "medico.id")
   private Medico medico;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "paciente.id")
   private Paciente paciente;

   private LocalDateTime data;

   @Column(name = "motivo_cancelamento")
   @Enumerated(EnumType.STRING)
   private MotivoCancelamento motivoCancelamento;

   

   public void cancelar(MotivoCancelamento motivoCancelamento) {
      this.motivoCancelamento = motivoCancelamento;
   }
}

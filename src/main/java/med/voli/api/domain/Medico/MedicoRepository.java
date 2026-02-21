package med.voli.api.domain.Medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import med.voli.api.domain.Enums.Especialidade;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
     Page<Medico> findAllByAtivoTrue(Pageable paginacao);

     boolean existsByIdAndAtivoTrue(Long id);

     @Query(value = """
                   select * from medicos m
                   where m.ativo = 1
                     and m.especialidade = :especialidade
                     and m.id not in (
                         select c.medico_id from consultas c
                         where c.data = :data
                           and c.motivo_cancelamento is null
                     )
                   order by rand()
                   limit 1
               """, nativeQuery = true)
     Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade,LocalDateTime data);
}

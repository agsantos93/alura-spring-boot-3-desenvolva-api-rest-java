package med.voll.api.repositorios;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.dto.Especialidade;
import med.voll.api.jpa.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByActiveTrue(Pageable paginacao);

    @Query("""
            select m from Medico m
            where m.active
                and m.especialidade = :especialidade
                and m.id not in(
                    select c.medico.id from Consulta c
                    where c.data = :data
                        and c.motivoCancelamento is null
                 )
            order by random()
            limit 1
           """)
    Medico medicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
        select m from Medico m
        where m.active and m.id = :idMedico
        """)
    Medico findActiveById(Long idMedico);

}

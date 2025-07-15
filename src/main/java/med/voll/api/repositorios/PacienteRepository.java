package med.voll.api.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.jpa.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByActiveTrue(Pageable paginacao);

    @Query("""
        select p from Paciente p
        where p.active and p.id = :idPaciente
        """)
    Paciente findActiveById(Long idPaciente);
}

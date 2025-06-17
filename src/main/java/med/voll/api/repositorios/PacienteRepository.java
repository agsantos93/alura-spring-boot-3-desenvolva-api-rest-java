package med.voll.api.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.jpa.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByActiveTrue(Pageable paginacao);

}

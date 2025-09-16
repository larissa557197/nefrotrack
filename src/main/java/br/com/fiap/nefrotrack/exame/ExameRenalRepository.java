package br.com.fiap.nefrotrack.exame;

import br.com.fiap.nefrotrack.paciente.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExameRenalRepository extends CrudRepository<ExameRenal, Long> {

    List<ExameRenal> findByPacienteOrderByDataDesc(Paciente paciente);

}

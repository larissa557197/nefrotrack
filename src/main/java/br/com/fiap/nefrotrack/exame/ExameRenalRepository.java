package br.com.fiap.nefrotrack.exame;

import br.com.fiap.nefrotrack.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ExameRenalRepository extends JpaRepository<ExameRenal, Long> {
    List<ExameRenal> findByPacienteOrderByDataDesc(Paciente paciente);
}
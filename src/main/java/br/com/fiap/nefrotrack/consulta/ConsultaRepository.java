package br.com.fiap.nefrotrack.consulta;

import br.com.fiap.nefrotrack.paciente.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsultaRepository extends CrudRepository<Consulta, Long> {

   List<Consulta> findByPacienteOrderByDataHoraDesc(Paciente paciente);
}

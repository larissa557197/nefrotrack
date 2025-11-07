package br.com.fiap.nefrotrack.consulta;

import br.com.fiap.nefrotrack.paciente.Paciente;
import br.com.fiap.nefrotrack.paciente.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;

    public List<Consulta> listarConsultas(Long pacienteId) {
        if (pacienteId != null) {
            Paciente paciente = pacienteRepository.findById(pacienteId).orElseThrow();
            return consultaRepository.findByPacienteOrderByDataHoraDesc(paciente);
        }
        return StreamSupport
                .stream(consultaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id).orElseThrow();
    }

    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public void excluir(Long id) {
        consultaRepository.deleteById(id);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }
}
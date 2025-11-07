package br.com.fiap.nefrotrack.exame;

import br.com.fiap.nefrotrack.paciente.Paciente;
import br.com.fiap.nefrotrack.paciente.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExameRenalService {

    private final ExameRenalRepository exameRepo;
    private final PacienteRepository pacienteRepo;

    public List<ExameRenal> listarExames(Long pacienteId) {
        if (pacienteId != null) {
            Paciente p = pacienteRepo.findById(pacienteId).orElseThrow();
            return exameRepo.findByPacienteOrderByDataDesc(p);
        }
        return exameRepo.findAll();
    }

    public ExameRenal buscarPorId(Long id) {
        return exameRepo.findById(id).orElseThrow();
    }

    public ExameRenal salvar(ExameRenal exame) {
        return exameRepo.save(exame);
    }

    public void excluir(Long id) {
        exameRepo.deleteById(id);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepo.findAll();
    }

    public TipoExame[] listarTipos() {
        return TipoExame.values();
    }
}

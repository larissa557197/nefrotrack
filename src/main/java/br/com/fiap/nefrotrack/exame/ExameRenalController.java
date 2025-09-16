package br.com.fiap.nefrotrack.exame;


import br.com.fiap.nefrotrack.paciente.Paciente;
import br.com.fiap.nefrotrack.paciente.PacienteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exames")
@RequiredArgsConstructor
public class ExameRenalController {

    private final ExameRenalRepository repo;
    private final PacienteRepository pacienteRepo;

    @GetMapping
    public String list(@RequestParam(required = false) Long pacienteId, Model model) {
        if (pacienteId != null) {
            Paciente p = pacienteRepo.findById(pacienteId).orElseThrow();
            model.addAttribute("exames", repo.findByPacienteOrderByDataDesc(p));
            model.addAttribute("pacienteSelecionado", p);
        } else {
            model.addAttribute("exames", repo.findAll());
        }
        model.addAttribute("pacientes", pacienteRepo.findAll());
        model.addAttribute("tipos", TipoExame.values());
        return "exame/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("exame", new ExameRenal());
        model.addAttribute("pacientes", pacienteRepo.findAll());
        model.addAttribute("tipos", TipoExame.values());
        return "exame/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("exame") ExameRenal exame, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("pacientes", pacienteRepo.findAll());
            model.addAttribute("tipos", TipoExame.values());
            return "exame/form";
        }
        repo.save(exame);
        return "redirect:/exames";
    }

    @GetMapping("/{id}/editar")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("exame", repo.findById(id).orElseThrow());
        model.addAttribute("pacientes", pacienteRepo.findAll());
        model.addAttribute("tipos", TipoExame.values());
        return "exame/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("exame") ExameRenal exame, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("pacientes", pacienteRepo.findAll());
            model.addAttribute("tipos", TipoExame.values());
            return "exame/form";
        }
        exame.setId(id);
        repo.save(exame);
        return "redirect:/exames";
    }

    @PostMapping("/{id}/excluir")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/exames";
    }
}

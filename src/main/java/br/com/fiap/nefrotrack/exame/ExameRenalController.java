package br.com.fiap.nefrotrack.exame;


import br.com.fiap.nefrotrack.paciente.Paciente;
import br.com.fiap.nefrotrack.paciente.PacienteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("formAction", "/exames");
        return "exame/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("exame") ExameRenal exame,
                         BindingResult br,
                         Model model,
                         RedirectAttributes ra) {
        if (br.hasErrors()) {
            model.addAttribute("pacientes", pacienteRepo.findAll());
            model.addAttribute("tipos", TipoExame.values());
            model.addAttribute("formAction", "/exames");
            ra.addFlashAttribute("error", "Corrija os erros do formulário.");
            return "exame/form";
        }
        repo.save(exame);
        ra.addFlashAttribute("success", "Exame criado com sucesso!");
        return "redirect:/exames";
    }

    @GetMapping("/{id}/editar")
    public String edit(@PathVariable Long id, Model model, RedirectAttributes ra) {
        ExameRenal exame = repo.findById(id).orElse(null);
        if (exame == null) {
            ra.addFlashAttribute("error", "Exame não encontrado.");
            return "redirect:/exames";
        }
        model.addAttribute("exame", exame);
        model.addAttribute("pacientes", pacienteRepo.findAll());
        model.addAttribute("tipos", TipoExame.values());
        model.addAttribute("formAction", "/exames/" + id);
        return "exame/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("exame") ExameRenal exame,
                         BindingResult br,
                         Model model,
                         RedirectAttributes ra) {
        if (br.hasErrors()) {
            model.addAttribute("pacientes", pacienteRepo.findAll());
            model.addAttribute("tipos", TipoExame.values());
            model.addAttribute("formAction", "/exames/" + id);
            ra.addFlashAttribute("error", "Corrija os erros do formulário.");
            return "exame/form";
        }
        exame.setId(id);
        repo.save(exame);
        ra.addFlashAttribute("success", "Exame atualizado com sucesso!");
        return "redirect:/exames";
    }

    @PostMapping("/{id}/excluir")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            ra.addFlashAttribute("success", "Exame excluído com sucesso!");
        } else {
            ra.addFlashAttribute("error", "Exame não encontrado.");
        }
        return "redirect:/exames";
    }
}

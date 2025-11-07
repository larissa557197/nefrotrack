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

    private final ExameRenalService exameService;

    @GetMapping
    public String list(@RequestParam(required = false) Long pacienteId, Model model) {
        model.addAttribute("exames", exameService.listarExames(pacienteId));
        model.addAttribute("pacientes", exameService.listarPacientes());
        model.addAttribute("tipos", exameService.listarTipos());
        return "exame/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("exame", new ExameRenal());
        model.addAttribute("pacientes", exameService.listarPacientes());
        model.addAttribute("tipos", exameService.listarTipos());
        return "exame/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("exame") ExameRenal exame,
                         BindingResult br,
                         Model model,
                         RedirectAttributes ra) {
        if (br.hasErrors()) {
            model.addAttribute("pacientes", exameService.listarPacientes());
            model.addAttribute("tipos", exameService.listarTipos());
            ra.addFlashAttribute("error", "Please correct the form errors.");
            return "exame/form";
        }
        exameService.salvar(exame);
        ra.addFlashAttribute("success", "Exam created successfully!");
        return "redirect:/exames";
    }

    @GetMapping("/{id}/editar")
    public String edit(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            model.addAttribute("exame", exameService.buscarPorId(id));
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Exam not found.");
            return "redirect:/exames";
        }
        model.addAttribute("pacientes", exameService.listarPacientes());
        model.addAttribute("tipos", exameService.listarTipos());
        return "exame/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("exame") ExameRenal exame,
                         BindingResult br,
                         Model model,
                         RedirectAttributes ra) {
        if (br.hasErrors()) {
            model.addAttribute("pacientes", exameService.listarPacientes());
            model.addAttribute("tipos", exameService.listarTipos());
            ra.addFlashAttribute("error", "Please correct the form errors.");
            return "exame/form";
        }
        exame.setId(id);
        exameService.salvar(exame);
        ra.addFlashAttribute("success", "Exam updated successfully!");
        return "redirect:/exames";
    }

    @PostMapping("/{id}/excluir")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            exameService.excluir(id);
            ra.addFlashAttribute("success", "Exam deleted successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Exam not found.");
        }
        return "redirect:/exames";
    }
}

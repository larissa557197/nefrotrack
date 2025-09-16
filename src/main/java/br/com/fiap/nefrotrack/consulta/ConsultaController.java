package br.com.fiap.nefrotrack.consulta;


import br.com.fiap.nefrotrack.paciente.Paciente;
import br.com.fiap.nefrotrack.paciente.PacienteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaRepository repo;
    private final PacienteRepository pacienteRepo;

    @GetMapping
    public String list(@RequestParam(required = false) Long pacienteId, Model model) {
        if (pacienteId != null) {
            Paciente p = pacienteRepo.findById(pacienteId).orElseThrow();
            model.addAttribute("consultas", repo.findByPacienteOrderByDataHoraDesc(p));
            model.addAttribute("pacientes", p);
        } else {
            model.addAttribute("consultas", repo.findAll());
        }
        model.addAttribute("pacientes", pacienteRepo.findAll());
        return "consulta/list";
    }

    @GetMapping("/novo")
    public String form(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", pacienteRepo.findAll());
        return "consulta/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("consulta") Consulta consulta, BindingResult br,  Model model) {
        if (br.hasErrors()) {
            model.addAttribute("pacientes", pacienteRepo.findAll());
            return "consulta/form";
        }
        repo.save(consulta);
        return "redirect:/consultas";
    }

    @PostMapping("/{id}/editar")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("consulta", repo.findById(id).orElseThrow());
        model.addAttribute("pacientes", pacienteRepo.findAll());
        return "consulta/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("consulta") Consulta consulta, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("pacientes", pacienteRepo.findAll());
            return "consulta/form";
        }
        consulta.setId(id);
        repo.save(consulta);
        return "redirect:/consultas";
    }

    @PostMapping("/{id}/excluir")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/consultas";
    }

}

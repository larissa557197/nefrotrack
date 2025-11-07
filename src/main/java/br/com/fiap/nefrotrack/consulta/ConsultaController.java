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

    private final ConsultaService consultaService;

    @GetMapping
    public String list(@RequestParam(required = false) Long pacienteId, Model model) {
        model.addAttribute("consultas", consultaService.listarConsultas(pacienteId));
        model.addAttribute("pacientes", consultaService.listarPacientes());
        return "consulta/list";
    }

    @GetMapping("/novo")
    public String form(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", consultaService.listarPacientes());
        return "consulta/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("consulta") Consulta consulta,
                         BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("pacientes", consultaService.listarPacientes());
            return "consulta/form";
        }
        consultaService.salvar(consulta);
        return "redirect:/consultas/";
    }

    @GetMapping("/{id}/editar")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("consulta", consultaService.buscarPorId(id));
        model.addAttribute("pacientes", consultaService.listarPacientes());
        return "consulta/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("consulta") Consulta consulta,
                         BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("pacientes", consultaService.listarPacientes());
            return "consulta/form";
        }
        consulta.setId(id);
        consultaService.salvar(consulta);
        return "redirect:/consultas/";
    }

    @PostMapping("/{id}/excluir")
    public String delete(@PathVariable Long id) {
        consultaService.excluir(id);
        return "redirect:/consultas/";
    }
}
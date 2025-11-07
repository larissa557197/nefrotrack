package br.com.fiap.nefrotrack.paciente;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "paciente/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "paciente/form";
    }

    @PostMapping
    public String create(@Valid Paciente paciente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "paciente/form";
        }
        pacienteService.salvar(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", pacienteService.buscarPorId(id));
        return "paciente/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid Paciente paciente,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "paciente/form";
        }
        paciente.setId(id);
        pacienteService.salvar(paciente);
        return "redirect:/pacientes";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        pacienteService.excluir(id);
        redirectAttributes.addFlashAttribute("success", "Paciente excluído com sucesso!");
        return "redirect:/pacientes";
    }
}
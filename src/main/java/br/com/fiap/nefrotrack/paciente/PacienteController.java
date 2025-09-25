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
    private final PacienteRepository pacienteRepository;

    @GetMapping
    public String pacientes(Model model) {
        model.addAttribute("pacientes", pacienteRepository.findAll());
        model.addAttribute("formAction", "/pacientes");
        return "paciente/list"; //templates/paciente/list.html
    }

    @GetMapping("/novo")
    public String form(Model model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("formAction", "/pacientes");
        return "paciente/form"; //templates/paciente/form.html
    }

    @PostMapping
    public String create(@Valid Paciente paciente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/pacientes");
            return "paciente/form";
        }

        pacienteRepository.save(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Paciente inválido: " + id));
        model.addAttribute("paciente", paciente);
        model.addAttribute("formAction", "/pacientes/" + id);
        return "paciente/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, Paciente paciente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/pacientes/" + id);
            return "paciente/form";
        }
        paciente.setId(id);
        pacienteRepository.save(paciente);
        return "redirect:/pacientes";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        pacienteRepository.deleteById(id);
        return "redirect:/pacientes";
    }

}

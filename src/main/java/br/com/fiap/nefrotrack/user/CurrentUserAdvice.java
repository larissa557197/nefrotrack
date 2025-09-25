package br.com.fiap.nefrotrack.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class CurrentUserAdvice {

    private final UserRepository userRepository;

    @ModelAttribute
    public void addAttributes(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            String githubId = principal.getAttribute("id").toString();
            User user = userRepository.findByGithubId(githubId).orElse(null);
            model.addAttribute("currentUser", user);
        }
    }
}

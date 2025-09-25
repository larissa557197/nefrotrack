package br.com.fiap.nefrotrack.auth;


import br.com.fiap.nefrotrack.user.User;
import br.com.fiap.nefrotrack.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

@Component
@RequiredArgsConstructor
public class Login implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UserService userService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof OAuth2User oAuth2User) {
            User user = userService.registerOrGet(oAuth2User);

            System.out.println("Usuário logado:" + user.getName());
        }
    }

}

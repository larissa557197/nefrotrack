package br.com.fiap.nefrotrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/error",
                                "/css/**", "/js/**", "/images/**", "/webjars/**",
                                "/oauth2/**", "/login/**"   // <-- LIBERADAS!
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // use OAuth2 (pode tirar o formLogin se não for usar)
                .oauth2Login(o -> o
                        .loginPage("/")                  // sua home tem o link "Entrar com GitHub"
                        .defaultSuccessUrl("/", true)    // para onde voltar depois de logar
                )
                .logout(l -> l.logoutSuccessUrl("/").permitAll());       // ajuste conforme seu projeto

        return http.build();
    }
}

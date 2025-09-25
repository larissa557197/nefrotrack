NefroTrack
---
**Gerencie paciente, exames renais e consultas** em uma aplicação web simples, segura e responsiva
---
✨ Recursos
- 👥 Pacientes: CRUD completo
- 🧪 Exames renais: tipos, resultado, observação e vínculo com paciente
- 🗓️ Consultas: data/hora, médico e anotações
- 🔐 Login OAuth2 com GitHub
- 🌍 i18n (en_US / pt_BR) para mensagens e validações
- 🧭 Layout responsivo com Bootstrap 5
- 🛠️ Migrations com Flyway
- 🧰 Boas práticas: @Controller + camadas, JPA, validação (Bean Validation), CSRF
---
🏗️ Arquitetura & Tech

- Back-end: Spring Boot (Web, Security, OAuth2 Client, Validation, Data JPA)
- UI: Thymeleaf + Bootstrap 5
- DB: PostgreSQL (Flyway para versionamento)
- Build: Gradle
- JDK: 17

src/
 └─ main/
    ├─ java/br/com/fiap/nefrotrack/...
    │   ├─ auth/         (# OAuth2 listener, security helpers)
    │   ├─ config/       (MessageSource, helpers)
    │   ├─ consulta/     (domain + controller + repo)
    │   ├─ exame/        (domain + controller + repo)
    │   ├─ paciente/     (domain + controller + repo)
    │   └─ user/         (entity User, CurrentUserAdvice, service, repo)
    └─ resources/
        ├─ db/migration/ (Flyway)
        ├─ static/       (css/js)
        ├─ templates/
        │   ├─ fragments/layout.html
        │   ├─ paciente/{list,form}.html
        │   ├─ exame/{list,form}.html
        │   └─ consulta/{list,form}.html
        ├─ messages.properties
        ├─ messages_pt_BR.properties
        └─ application.properties

---
🚀 Subindo o projeto
1) Pré-requisitos
- Java 17
- PostgreSQL rodando (ou Docker)
- Gradle (wrapper já incluso)
- Credenciais OAuth no GitHub (OAuth App)


RM558042 Thomas Rodrigues Ribeiro Silva

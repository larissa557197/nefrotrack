# NefroTrack
---
**Gerencie paciente, exames renais e consultas** em uma aplicação web simples, segura e responsiva
---
## ✨ Recursos
- 👥 Pacientes: CRUD completo
- 🧪 Exames renais: tipos, resultado, observação e vínculo com paciente
- 🗓️ Consultas: data/hora, médico e anotações
- 🔐 Login OAuth2 com GitHub
- 🌍 i18n (en_US / pt_BR) para mensagens e validações
- 🧭 Layout responsivo com Bootstrap 5
- 🛠️ Migrations com Flyway
- 🧰 Boas práticas: @Controller + camadas, JPA, validação (Bean Validation), CSRF
---
## 🏗️ Arquitetura & Tech

- Back-end: Spring Boot (Web, Security, OAuth2 Client, Validation, Data JPA)
- UI: Thymeleaf + Bootstrap 5
- DB: PostgreSQL (Flyway para versionamento)
- Build: Gradle
- JDK: 17

---
## 🚀 Subindo o projeto
1) Pré-requisitos
- Java 17
- PostgreSQL rodando (ou Docker)
- Gradle (wrapper já incluso)
- Credenciais OAuth no GitHub (OAuth App)
  
2) Variáveis de Ambiente
   ```
    # Client ID do github
    spring.security.oauth2.client.registration.github.client-id=${GITHUB_ID}
    spring.security.oauth2.client.registration..github.client-secret=${GITHUB_SECRET}
   ```
3) Rodando
   ```
   http:localhost:8080
   ```
---
## Login com o Github
1. Crie um **OAuth App** em `https://github.com/settings/developers`
2. **Homepage URL:** `http://localhost:8080`
3. **Authorization callback URL:** `http://localhost:8080/login/oauth2/code/github`
4. Copie **Client ID** e **Client Secret** para `GITHUB_ID` e `GITHUB_SECRET`
5. Acesse **Entrar com GitHub** na navbar

---
## 🌐 Internacionalização (i18n)
- `messages.properties` (default, EN)
- `messages_pt_BR.properties` (português)
- `ValidationMessages_*.properties` para mensagens de validação
Exemplo no Thymeleaf:
```
<h2 th:text="#{pacientes.title}"> Pacient List </h2>
<button th:text="#{btn.new}"> New </button>
```
---
## Integrantes
RM557197 - Larissa Mezencio Pereira Muniz
RM558042 - Thomas Rodrigues Ribeiro Silva

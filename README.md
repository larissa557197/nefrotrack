# NefroTrack
---
**Gerencie paciente, exames renais e consultas** em uma aplicação web simples, segura e responsiva
---
## 📑 **Índice**

- [Visão Geral](#-visão-geral)
- [Tecnologias](#-tecnologias)
- [Funcionalidades](#-funcionalidades)
- [Arquitetura](#-arquitetura)
- [Instalação](#-instalação)
- [Configuração](#-configuração)
- [Execução](#-execução)
- [Endpoints](#-endpoints)
- [Screenshots](#-screenshots)
- [Equipe](#-equipe)
- [Licença](#-licença)

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
## 🎯 **Visão Geral**
O **NefroTrack** é uma aplicação para gerenciar o ciclo clínico de pacientes renais, incluindo:

- Cadastro e gerenciamento de **pacientes**;
- Registro e acompanhamento de **exames renais**;
- Agendamento de **consultas médicas**;
- Dashboard com **resumo clínico**;
- **Autenticação via GitHub OAuth2**.
---
## 🛠 **Tecnologias**

O projeto foi desenvolvido com as seguintes tecnologias:

- **Back-end:**  
  - Spring Boot 3 (Web, Data JPA, Security, Validation, OAuth2 Client)
  - Thymeleaf para a camada de visualização
  - PostgreSQL com Flyway para migrações

- **Autenticação:**  
  - Spring Security com OAuth2 Client para **GitHub OAuth2**

- **Dev Tools:**  
  - Lombok, Maven, Docker Compose, Spring DevTools
---
## 🚀 **Funcionalidades**

### ✅ Principais Funcionalidades:
- Cadastro e gerenciamento de **pacientes** com informações de contato e histórico.
- Registro e consulta de **exames renais** (creatinina, ureia, potássio, sódio, etc.).
- Agendamento e acompanhamento de **consultas médicas**.
- Dashboard de **estados clínicos** de pacientes, exames e consultas.
- **Filtros e busca** para visualizar dados específicos de pacientes e exames.
- **Autenticação GitHub** para login.

### 🛠 Funcionalidades Planejadas:
- Alertas clínicos para **valores críticos** de exames.
- Estatísticas detalhadas sobre exames e consultas.

---
## 🏗 **Arquitetura do Projeto**
O projeto NefroTrack segue uma arquitetura limpa e modularizada, visando boa organização e fácil manutenção. Abaixo está a estrutura de pastas e a descrição de cada módulo:
├── 📦 **src**
│   ├── 🛠️ **main**
│   │   ├── 🌐 **java**
│   │   │   └── br
│   │   │       └── com
│   │   │           └── fiap
│   │   │               └── nefrotrack
│   │   │                   ├── **exame**                  # Módulo de Exames
│   │   │                   │   ├── ExameRenalController.java  # Lógica para gerenciamento de exames
│   │   │                   │   ├── ExameRenalRepository.java  # Acesso ao banco de dados para Exames
│   │   │                   │   └── TipoExame.java            # Enum com os tipos de exames
│   │   │                   ├── **paciente**               # Módulo de Pacientes
│   │   │                   │   ├── PacienteController.java   # Lógica para gerenciamento de pacientes
│   │   │                   │   ├── PacienteRepository.java   # Acesso ao banco de dados para Pacientes
│   │   │                   │   └── Paciente.java             # Entidade Paciente
│   │   │                   ├── **consulta**               # Módulo de Consultas
│   │   │                   │   ├── ConsultaController.java   # Lógica para gerenciamento de consultas
│   │   │                   │   ├── ConsultaRepository.java   # Acesso ao banco de dados para Consultas
│   │   │                   │   └── Consulta.java             # Entidade Consulta
│   │   │                   ├── **user**                   # Módulo de Autenticação
│   │   │                   │   ├── UserController.java       # Lógica de controle de usuários
│   │   │                   │   ├── UserRepository.java       # Acesso ao banco de dados para Usuários
│   │   │                   │   └── User.java                 # Entidade de Usuário
│   │   │                   └── **security**                # Módulo de Segurança (Autenticação/Autorização)
│   │   │                       ├── SecurityConfig.java        # Configuração de segurança (OAuth2, JWT, etc.)
│   │   │                       └── CurrentUserAdvice.java     # Serviço para acessar o usuário logado
│   │   ├── **resources**
│   │   │   └── **static**
│   │   │       └── **css**           # Estilos CSS do Frontend
│   │   │       └── **js**            # Scripts JS
│   │   └── **templates**
│   │       ├── layout.html          # Layout base do projeto
│   │       ├── paciente
│   │       │   ├── list.html        # Tela de listagem de pacientes
│   │       │   └── form.html        # Formulário para criação/edição de pacientes
│   │       ├── exame
│   │       │   ├── list.html        # Tela de listagem de exames
│   │       │   └── form.html        # Formulário para criação/edição de exames
│   │       └── consulta
│   │           ├── list.html        # Tela de listagem de consultas
│   │           └── form.html        # Formulário para criação/edição de consultas
│   └── **test**                     # Testes Unitários e de Integração
│       ├── **java**
│       └── **resources**
└── **pom.xml**                      # Arquivo de configuração do Maven

🔧 Descrição dos Módulos

`exame`:

- Controller: Lógica para o gerenciamento de exames renais.
- Repository: Acesso ao banco de dados para salvar, buscar e excluir exames.
- TipoExame: Enum que define os tipos de exames realizados (Creatinina, Ureia, etc.).

`paciente`:
- Controller: Lógica para o gerenciamento de pacientes.
- Repository: Acesso ao banco de dados para pacientes.
- Paciente: Entidade que representa as informações do paciente.

`consulta`:
- Controller: Lógica para o gerenciamento de consultas médicas.
- Repository: Acesso ao banco de dados para consultas.
- Consulta: Entidade que representa uma consulta médica.

`user`:
- Controller: Controle de funcionalidades relacionadas ao usuário (login, logout, etc.).
- Repository: Acesso ao banco de dados para usuários.
- User: Entidade que representa as informações de cada usuário.

`security`:
- SecurityConfig: Configuração de segurança, autenticação via OAuth2 e controle de acessos.
- CurrentUserAdvice: Classe que permite acessar as informações do usuário autenticado.

📦 Diretórios:
- `src/main/resources/static/css`: Arquivos CSS responsáveis pela estilização do frontend.
- `src/main/resources/static/js`: Scripts JavaScript para funcionalidades do frontend.
- `src/main/resources/templates`: Templates Thymeleaf para renderizar as páginas HTML.
- `src/test/java`: Arquivos de teste para garantir a qualidade do código.

---
## ⚙️ **Instalação**

### Pré-requisitos
- **Java 17+**
- **Maven 3.9+**
- **PostgreSQL 15+** ou **Docker**

### Clonar o Repositório
```bash
git clone https://github.com/seu-usuario/nefrotrack.git
cd nefrotrack
```
---
🔑 Configuração
`application.properties`

Crie um arquivo de configuração com as seguintes propriedades:
---

▶️ Execução
```
mvn spring-boot:run
```
Acesse:
👉 `http://localhost:8080`

---
## 📡 **Endpoints**

### Pacientes

| Método | Endpoint               | Descrição                           |
|--------|------------------------|-------------------------------------|
| GET    | `/pacientes`            | Retorna a lista de todos os pacientes |
| POST   | `/pacientes`            | Cria um novo paciente               |
| GET    | `/pacientes/{id}`       | Exibe os detalhes de um paciente específico |
| POST   | `/pacientes/{id}/excluir` | Exclui um paciente pelo ID          |

### Exames

| Método | Endpoint               | Descrição                           |
|--------|------------------------|-------------------------------------|
| GET    | `/exames`              | Retorna a lista de todos os exames  |
| POST   | `/exames`              | Registra um novo exame              |
| GET    | `/exames/{id}`         | Exibe os detalhes de um exame específico |
| POST   | `/exames/{id}/excluir` | Exclui um exame pelo ID             |

### Consultas

| Método | Endpoint               | Descrição                           |
|--------|------------------------|-------------------------------------|
| GET    | `/consultas`           | Retorna a lista de todas as consultas |
| POST   | `/consultas`           | Agenda uma nova consulta           |
| GET    | `/consultas/{id}`      | Exibe os detalhes de uma consulta específica |
| POST   | `/consultas/{id}/excluir` | Exclui uma consulta pelo ID        |

### Autenticação

| Método | Endpoint                         | Descrição                           |
|--------|----------------------------------|-------------------------------------|
| GET    | `/oauth2/authorization/github`   | Inicia o fluxo de autenticação via GitHub |
| GET    | `/logout`                        | Faz o logout da aplicação          |



---
## 👥 Integrantes
| 👥 |    RM    |              Nome              |
|----|----------|---------------------------------|
| 👩 | RM557197 | Larissa Mezencio Pereira Muniz |
| 👩 | RM558042 | Thomas Rodrigues Ribeiro Silva |
 

 

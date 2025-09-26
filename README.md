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
## 🏗 **Arquitetura**

A arquitetura do projeto segue o modelo **MVC** (Model-View-Controller):
src/main/java/br/com/fiap/nefrotrack
├── config/ # Configurações de segurança e internacionalização (i18n)
├── consulta/ # CRUD de consultas
├── exame/ # CRUD de exames renais
├── paciente/ # CRUD de pacientes
├── user/ # Autenticação de usuários via GitHub OAuth2
└── NefrotrackApp # Classe principal
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
## Integrantes
RM557197 - Larissa Mezencio Pereira Muniz
RM558042 - Thomas Rodrigues Ribeiro Silva

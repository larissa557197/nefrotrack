CREATE TABLE paciente (
    id BIGSERIAL PRIMARY KEY, -- chave primária auto-incremento,
    nome VARCHAR(120) NOT NULL,
    data_nascimento DATE, --pode ser nulo, mas validado na aplicação com @Past,
    email VARCHAR(100) NOT NULL UNIQUE, -- para não ser duplicado,
    telefone VARCHAR(40)
)
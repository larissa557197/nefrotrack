CREATE TABLE consulta (
    id BIGSERIAL PRIMARY KEY,
    paciente_id BIGINT NOT NULL REFERENCES paciente(id) ON DELETE CASCADE,
    data_hora TIMESTAMP NOT NULL,
    medico_nome VARCHAR(140) NOT NULL,
    anotacoes VARCHAR(1000)
)
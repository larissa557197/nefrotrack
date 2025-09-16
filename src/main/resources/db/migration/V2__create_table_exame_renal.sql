CREATE TABLE exame_renal (
    id BIGSERIAL PRIMARY KEY,
    paciente_id BIGINT NOT NULL REFERENCES paciente(id) ON DELETE CASCADE,
    data DATE NOT NULL,
    tipo VARCHAR(40) NOT NULL,
    resultado NUMERIC(10,2) NOT NULL CHECK ( resultado >= 0 ),
    observacao VARCHAR(500)
)
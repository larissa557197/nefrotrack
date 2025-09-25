CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    avatar_url VARCHAR(500),
    github_id VARCHAR(50) UNIQUE NOT NULL,
    github_login VARCHAR(255) NOT NULL
)
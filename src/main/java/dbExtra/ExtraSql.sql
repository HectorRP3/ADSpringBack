CREATE TABLE primera_vez (
    id             SERIAL PRIMARY KEY,     
    programa       VARCHAR(100)  NOT NULL,
    fecha_emision  DATE          NOT NULL,
    joke_id        INT UNIQUE,             
    CONSTRAINT fk_primera_vez_joke
        FOREIGN KEY (joke_id) REFERENCES jokes(id)
);


CREATE TABLE telefonos (
    id               SERIAL PRIMARY KEY,
    numero           VARCHAR(20) NOT NULL,
    primera_vez_id   INT NOT NULL,
    CONSTRAINT fk_telefonos_primera_vez
        FOREIGN KEY (primera_vez_id) REFERENCES primera_vez(id)
);
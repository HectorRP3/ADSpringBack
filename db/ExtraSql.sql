CREATE TABLE primera_vez (
    id             SERIAL PRIMARY KEY,     
    programa       VARCHAR(100)  NOT NULL,
    fecha_emision  DATE          NOT NULL,
    idjoke        INT UNIQUE,             
    CONSTRAINT fk_primera_vez_joke
        FOREIGN KEY (idjoke) REFERENCES jokes(id)
);


CREATE TABLE telefonos (
    id               SERIAL PRIMARY KEY,
    numero           VARCHAR(20) NOT NULL,
    idprimeravez   INT NOT NULL,
    CONSTRAINT fk_telefonos_primera_vez
        FOREIGN KEY (idprimeravez) REFERENCES primera_vez(id)
);
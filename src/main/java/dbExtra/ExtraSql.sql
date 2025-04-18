CREATE TABLE primera_vez (
    id INT PRIMARY KEY, 
    programa VARCHAR(100) NOT NULL,
    fecha_emision DATE NOT NULL,
    idjoke INT UNIQUE,  
    FOREIGN KEY (id) REFERENCES jokes(id)
);


CREATE TABLE telefonos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(20) NOT NULL,
    idprimeravez INT NOT NULL,
    FOREIGN KEY (idprimeravez) REFERENCES primera_vez(id)
);

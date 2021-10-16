-- Table para manejar el estado de los bienes
CREATE SEQUENCE sec_estado
    INCREMENT 1
    START 1;

CREATE TABLE estado_bienes
(
    id          int4 NOT NULL DEFAULT nextval('sec_estado'),
    nombre      varchar(255),
    descripcion varchar(255),
    PRIMARY KEY (id)
);

-- Table para registro de las categorias de los bienes
CREATE SEQUENCE sec_categorias
    INCREMENT 1
    START 1;

CREATE TABLE categorias
(
    id     integer      NOT NULL DEFAULT nextval('sec_categorias'),
    nombre varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Table para registro y control del stock de bienes
CREATE SEQUENCE sec_bienes
    INCREMENT 1
    START 1;

CREATE TABLE bienes
(
    id           integer      NOT NULL DEFAULT nextval('sec_bienes'),
    nombre       varchar(255) NOT NULL,
    stock        integer      NOT NULL,
    id_estado    int4         NOT NULL,
    id_categoria integer      NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE bienes
    ADD CONSTRAINT fk_estado_bienes FOREIGN KEY (id_estado) REFERENCES estado_bienes;

ALTER TABLE bienes
    ADD CONSTRAINT fk_categoria_bien FOREIGN KEY (id_categoria) REFERENCES categorias;

-- Table para registrar auditoria de las modificaciones de bienes
CREATE SEQUENCE sec_auditoria_bienes
    INCREMENT 1
    START 1;

CREATE TABLE auditoria_bienes
(
    id        integer   NOT NULL DEFAULT nextval('sec_auditoria_bienes'),
    id_bien   integer   NOT NULL,
    pre_data  json      NOT NULL,
    post_data json      NOT NULL,
    fecha     timestamp NOT NULL default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE auditoria_bienes
    ADD CONSTRAINT fk_id_bien_auditoria FOREIGN KEY (id_bien) REFERENCES bienes;

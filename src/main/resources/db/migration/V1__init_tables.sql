-- Table para manejar el tipo de bienes
CREATE SEQUENCE sec_tipo
    INCREMENT 1
    START 1;

CREATE TABLE tipo
(
    id          int4 NOT NULL DEFAULT nextval('sec_tipo'),
    descripcion varchar(255),
    PRIMARY KEY (id)
);

-- Table para registro y control del stock de bienes
CREATE SEQUENCE sec_bienes
    INCREMENT 1
    START 1;

CREATE TABLE bienes
(
    id      integer      NOT NULL DEFAULT nextval('sec_bienes'),
    nombre  varchar(255) NOT NULL,
    stock   integer      NOT NULL,
    estado  int4         NOT NULL,
    id_tipo int4         NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE bienes
    ADD CONSTRAINT fk_tipo_bienes FOREIGN KEY (id_tipo) REFERENCES tipo;

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

-- Table intermedia entre categorias y bienes
CREATE SEQUENCE sec_bienes_categorias
    INCREMENT 1
    START 1;

CREATE TABLE bienes_categorias
(
    id           integer NOT NULL DEFAULT nextval('sec_bienes_categorias'),
    id_bien      integer NOT NULL,
    id_categoria integer NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE bienes_categorias
    ADD CONSTRAINT fk_id_bienes FOREIGN KEY (id_bien) REFERENCES bienes;

ALTER TABLE bienes_categorias
    ADD CONSTRAINT fk_id_categoria FOREIGN KEY (id_categoria) REFERENCES categorias;

-- Table para controlar las transacciones que se pueden realizar con los bienes
CREATE SEQUENCE sec_tipo_transaccion_bienes
    INCREMENT 1
    START 1;

CREATE TABLE tipo_transaccion_bienes
(
    id          int4 NOT NULL DEFAULT nextval('sec_tipo_transaccion_bienes'),
    descripcion varchar(255),
    PRIMARY KEY (id)
);

-- Table para registrar auditoria de las modificaciones de bienes
CREATE SEQUENCE sec_auditoria_bienes
    INCREMENT 1
    START 1;

CREATE TABLE auditoria_bienes
(
    id             integer   NOT NULL DEFAULT nextval('sec_auditoria_bienes'),
    id_bien        integer   NOT NULL,
    pre_data       json      NOT NULL,
    post_data      json      NOT NULL,
    id_transaccion int4      NOT NULL,
    fecha          timestamp NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE auditoria_bienes
    ADD CONSTRAINT fk_id_bien_auditoria FOREIGN KEY (id_bien) REFERENCES bienes;

ALTER TABLE auditoria_bienes
    ADD CONSTRAINT fk_id_tipo_transaccion_bien FOREIGN KEY (id_transaccion) REFERENCES tipo_transaccion_bienes;

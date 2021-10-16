-- Datos iniciales para estados de los bienes
INSERT INTO estado_bienes (nombre, descripcion)
VALUES ('Activo', 'Estado cuando un bien se encuentra en estado activo');
INSERT INTO estado_bienes (nombre, descripcion)
VALUES ('Dado de Baja', 'Estado cuando un bien se encuentra dado de baja');

-- Datos iniciales para categorias
INSERT INTO categorias (nombre)
VALUES ('Materia Prima');
INSERT INTO categorias (nombre)
VALUES ('Productos Terminados');
INSERT INTO categorias (nombre)
VALUES ('Suministros');

-- Datos iniciales para bienes
INSERT INTO bienes (nombre, stock, id_estado, id_categoria)
VALUES ('Bebidas energeticas', 200, 1, 1);
INSERT INTO bienes (nombre, stock, id_estado, id_categoria)
VALUES ('Tarjetas de regalo', 300, 1, 2);
INSERT INTO bienes (nombre, stock, id_estado, id_categoria)
VALUES ('Escritorio personal', 100, 1, 3);

-- Datos iniciales para auditoria_bienes
INSERT INTO auditoria_bienes (id_bien, pre_data, post_data)
VALUES (1, '{}', '{"id":1,"nombre":"Bebidas energeticas","stock":200,"id_estado":1,"id_categoria":1}');
INSERT INTO auditoria_bienes (id_bien, pre_data, post_data)
VALUES (2, '{}', '{"id":2,"nombre":"Tarjetas de regalo","stock":300,"id_estado":1,"id_categoria":2}');
INSERT INTO auditoria_bienes (id_bien, pre_data, post_data)
VALUES (3, '{}', '{"id":3,"nombre":"Escritorio personal","stock":100,"id_estado":1,"id_categoria":3}');

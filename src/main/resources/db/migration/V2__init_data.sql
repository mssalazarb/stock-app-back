-- Datos iniciales para tipos de bienes
INSERT INTO tipo (descripcion)
VALUES ('Bienes de Consumo');
INSERT INTO tipo (descripcion)
VALUES ('Bienes Economicos');
INSERT INTO tipo (descripcion)
VALUES ('Bienes Normales');

-- Datos iniciales para bienes
INSERT INTO bienes (nombre, stock, estado, id_tipo)
VALUES ('Bebidas energeticas', 200, 1, 1);
INSERT INTO bienes (nombre, stock, estado, id_tipo)
VALUES ('Tarjetas de regalo', 300, 1, 2);
INSERT INTO bienes (nombre, stock, estado, id_tipo)
VALUES ('Escritorio personal', 100, 1, 3);

-- Datos iniciales para categorias
INSERT INTO categorias (nombre)
VALUES ('Materia Prima');
INSERT INTO categorias (nombre)
VALUES ('Productos Terminados');
INSERT INTO categorias (nombre)
VALUES ('Suministros');

-- Datos iniciales para bienes_categorias
INSERT INTO bienes_categorias (id_bien, id_categoria)
VALUES (1, 2);
INSERT INTO bienes_categorias (id_bien, id_categoria)
VALUES (2, 3);
INSERT INTO bienes_categorias (id_bien, id_categoria)
VALUES (3, 3);

-- Datos iniciales para tipos de transacciones
INSERT INTO tipo_transaccion_bienes (descripcion)
VALUES ('Insercion');
INSERT INTO tipo_transaccion_bienes (descripcion)
VALUES ('Edicion');
INSERT INTO tipo_transaccion_bienes (descripcion)
VALUES ('Eliminacion');

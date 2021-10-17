package com.pichincha.stock.proyection;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * descripcion: Proyeccion para obtener datos de la tabla categoria
 */
public interface CategoriaProyection {
    Integer getId();

    String getNombre();

    void setId(Integer id);

    void setNombre(String nombre);
}

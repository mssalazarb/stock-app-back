package com.pichincha.stock.proyection;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * descripcion: Proyeccion para obtener datos de la tabla bienes sin la relacion de tipo_bien
 */
public interface BienProyection {
    Integer getId();

    String getNombre();

    Integer getStock();

    Integer getEstado();

    Integer getIdTipo();
}

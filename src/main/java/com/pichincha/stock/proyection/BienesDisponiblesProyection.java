package com.pichincha.stock.proyection;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * descripcion: Proyeccion para obtener todos los bienes disponibles por categoria
 */
public interface BienesDisponiblesProyection {
    Integer getDisponibles();

    String getCategoria();

    void setDisponibles(Integer disponibles);

    void setCategoria(String categoria);
}

package com.pichincha.stock.proyection;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * descripcion: Proyeccion para obtener todos los bienes disponibles por categoria
 */
public interface BienesDisponiblesDadosDeBajaProyection {
    Integer getDisponibles();

    Integer getDadosDeBaja();

    String getCategoria();
}

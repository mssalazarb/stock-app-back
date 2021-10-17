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

    Integer getIdEstado();

    Integer getIdCategoria();

    String getEstadoBien();

    void setId(Integer id);

    void setNombre(String nombre);

    void setStock(Integer stock);

    void setIdEstado(Integer idEstado);

    void setIdCategoria(Integer idCategoria);

    void setEstadoBien(String estadoBien);
}

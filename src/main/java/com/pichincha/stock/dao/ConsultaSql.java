package com.pichincha.stock.dao;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * descripcion: Constantes para consultas sql nativas
 */
public class ConsultaSql {

    public static final String FIND_ALL_BIEN = "select b.id, b.nombre, b.stock, b.estado, b.id_tipo as idTipo from bienes b";
    public static final String FIND_BY_ID_BIEN = "select b.id, b.nombre, b.stock, b.estado, b.id_tipo as idTipo from bienes b " +
            "where b.id = :id";

    public static final String COUNT_BIENES_BY_CATEGORIA = "select count(bc.id_bien) from bienes_categorias bc where id_categoria = :categoria";
}

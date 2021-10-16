package com.pichincha.stock.dao;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * descripcion: Constantes para consultas sql nativas
 */
public class ConsultaSql {

    public static final String FIND_ALL_BIEN = "select b.id, b.nombre, b.stock, b.id_estado as idEstado, " +
            "b.id_categoria as idCategoria, e.nombre as estadoBien " +
            "from bienes b " +
            "inner join estado_bienes e on e.id = b.id_estado";

    public static final String FIND_BY_ID_BIEN = "select b.id, b.nombre, b.stock, b.estado, b.id_tipo as idTipo from bienes b " +
            "where b.id = :id";

    public static final String BIENES_AVAILABLE_BY_CATEGORIA = "select count(b.id) as disponibles, c.nombre as categoria " +
            "from bienes b " +
            "inner join categorias c on c.id = b.id_categoria " +
            "where b.id_estado = 1 and b.stock > 0 " +
            "group by c.nombre";

    public static final String BIENES_AVAILABLE_WITHDRAWAL_BY_CATEGORIA = "select " +
            "(select count(bi.id) from bienes bi where bi.id_estado = 1 and bi.id_categoria = b.id_categoria) as disponibles, " +
            "(select count(bi.id) from bienes bi where bi.id_estado = 2 and bi.id_categoria = b.id_categoria) as dadosDeBaja, " +
            "c.nombre as categoria " +
            "from bienes b " +
            "inner join categorias c on c.id = b.id_categoria " +
            "where c.id = :categoria " +
            "group by c.nombre, b.id_categoria";

    public static final String FIND_ALL_CATEGORIA = "select c.id, c.nombre from categorias c";

    public static final String BIENES_BY_NAMES = "select b.id " +
            "from bienes b " +
            "where b.nombre in (:nombres) ";

    public static final String BIENES_BY_IDS = "select b.* " +
            "from bienes b " +
            "where b.id in (:ids) ";
}

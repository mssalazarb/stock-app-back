package com.pichincha.stock.dao;

import com.pichincha.stock.entity.BienCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * descripcion: Dao para la tabla bienes_categoria
 */
@Repository
public interface BienCategoriaDao extends JpaRepository<BienCategoria, Integer> {
}

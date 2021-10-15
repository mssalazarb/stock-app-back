package com.pichincha.stock.dao;

import com.pichincha.stock.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * descripcion: Dao para la tabla categoria
 */
@Repository
public interface CatagoriaDao extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNombre(String nombre);
}

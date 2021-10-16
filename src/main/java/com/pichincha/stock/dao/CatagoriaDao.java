package com.pichincha.stock.dao;

import com.pichincha.stock.entity.Categoria;
import com.pichincha.stock.proyection.CategoriaProyection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.pichincha.stock.dao.ConsultaSql.FIND_ALL_CATEGORIA;

/**
 * @author mssalazarb
 * @version 1
 */
@Repository
public interface CatagoriaDao extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNombre(String nombre);

    @Query(value = FIND_ALL_CATEGORIA, nativeQuery = true)
    List<CategoriaProyection> findAllWithoutDetail();
}

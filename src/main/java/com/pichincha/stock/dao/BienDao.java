package com.pichincha.stock.dao;

import com.pichincha.stock.entity.Bien;
import com.pichincha.stock.proyection.BienProyection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.pichincha.stock.dao.ConsultaSql.*;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * descripcion: Dao para la tabla bienes
 */
@Repository
public interface BienDao extends JpaRepository<Bien, Integer> {

    @Query(value = FIND_ALL_BIEN, nativeQuery = true)
    List<BienProyection> findAllBienes();

    @Query(value = FIND_BY_ID_BIEN, nativeQuery = true)
    Optional<BienProyection> findByIdBien(@Param("id") Integer id);

    Optional<Bien> findByNombre(String nombre);

    @Query(value = COUNT_BIENES_BY_CATEGORIA, nativeQuery = true)
    Integer findBienesByCategory(@Param("categoria") Integer id);
}

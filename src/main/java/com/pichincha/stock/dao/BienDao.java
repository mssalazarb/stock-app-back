package com.pichincha.stock.dao;

import com.pichincha.stock.entity.Bien;
import com.pichincha.stock.proyection.BienProyection;
import com.pichincha.stock.proyection.BienesDisponiblesDadosDeBajaProyection;
import com.pichincha.stock.proyection.BienesDisponiblesProyection;
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
 */
@Repository
public interface BienDao extends JpaRepository<Bien, Integer> {

    @Query(value = FIND_ALL_BIEN, nativeQuery = true)
    List<BienProyection> findAllBienesStatus();

    @Query(value = FIND_BY_ID_BIEN, nativeQuery = true)
    Optional<BienProyection> findByIdBien(@Param("id") Integer id);

    Optional<Bien> findByNombre(String nombre);

    @Query(value = BIENES_AVAILABLE_BY_CATEGORIA, nativeQuery = true)
    List<BienesDisponiblesProyection> findBienesDisponiblesByCategory();

    @Query(value = BIENES_AVAILABLE_WITHDRAWAL_BY_CATEGORIA, nativeQuery = true)
    List<BienesDisponiblesDadosDeBajaProyection> findBienesDisponiblesDadosDeBajaByCategory(Integer categoria);

    @Query(value = BIENES_BY_NAMES, nativeQuery = true)
    List<Integer> findBienesInNames(List<String> nombres);

    @Query(value = BIENES_BY_IDS, nativeQuery = true)
    List<Bien> findBienesInIds(List<Integer> ids);
}

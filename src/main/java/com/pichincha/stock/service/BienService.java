package com.pichincha.stock.service;

import com.pichincha.stock.entity.Bien;
import com.pichincha.stock.proyection.BienProyection;
import com.pichincha.stock.proyection.BienesDisponiblesDadosDeBajaProyection;
import com.pichincha.stock.proyection.BienesDisponiblesProyection;

import java.util.List;

public interface BienService {
    List<BienProyection> findAllBienesStatus();

    BienProyection findByNameBien(String name);

    Bien findByNameDetail(String nombre);

    Bien save(Bien bien);

    void saveAll(List<Bien> bienes);

    List<BienesDisponiblesProyection> findBienesDisponiblesByCategory();

    List<BienesDisponiblesDadosDeBajaProyection> findBienesDisponiblesDadosDeBajaByCategory(Integer categoria);

    void darDeBaja(List<Integer> ids);
}

package com.pichincha.stock.service;

import com.pichincha.stock.dao.BienDao;
import com.pichincha.stock.entity.Bien;
import com.pichincha.stock.exception.CustomRuntimeException;
import com.pichincha.stock.proyection.BienProyection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author mssalazarb
 * @version 1
 */
@Service
@RequiredArgsConstructor
public class BienService {

    private final BienDao bienDao;

    public List<BienProyection> findAll() {
        return bienDao.findAllBienes();
    }

    public BienProyection findById(Integer id) {
        return bienDao.findByIdBien(id).orElse(null);
    }

    public Bien findByIdDetail(Integer id) {
        return bienDao.findById(id).orElse(null);
    }

    @Transactional
    public Bien save(Bien bien) {
        var bienResp = bienDao.findByNombre(bien.getNombre()).orElse(null);
        if (!Objects.isNull(bienResp)) {
            throw new CustomRuntimeException("Ya existe un bien registrado con el nombre ingresado");
        }

        return bienDao.save(bienResp);
    }

    public Integer findBienesByCategory(Integer id) {
        return bienDao.findBienesByCategory(id);
    }
}

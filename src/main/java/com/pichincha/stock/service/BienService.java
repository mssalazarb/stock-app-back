package com.pichincha.stock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.stock.dao.BienDao;
import com.pichincha.stock.entity.Bien;
import com.pichincha.stock.entity.EmptyBien;
import com.pichincha.stock.exception.CustomRuntimeException;
import com.pichincha.stock.proyection.BienProyection;
import com.pichincha.stock.proyection.BienesDisponiblesDadosDeBajaProyection;
import com.pichincha.stock.proyection.BienesDisponiblesProyection;
import com.pichincha.stock.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author mssalazarb
 * @version 1
 */
@Service
@RequiredArgsConstructor
@CommonsLog
public class BienService {

    private final BienDao bienDao;
    private final AuditoriaService auditoriaService;
    private final ObjectMapper mapper;

    /**
     * buscar todos los bienes con el detalle de su respectivo estado
     */
    public List<BienProyection> findAllBienesStatus() {
        return bienDao.findAllBienesStatus();
    }

    /**
     * buscar un bien por id
     *
     * @param id - id del bien
     */
    public BienProyection findById(Integer id) {
        return bienDao.findByIdBien(id).orElse(null);
    }

    /**
     * buscar un bien con su detalle por id
     *
     * @param id - id del bien
     */
    public Bien findByIdDetail(Integer id) {
        return bienDao.findById(id).orElse(null);
    }

    /**
     * registrar un nuevo bien
     *
     * @param bien - object de tipo Bien a ser guardado
     */
    @Transactional
    public Bien save(Bien bien) {
        var bienResp = bienDao.findByNombre(bien.getNombre()).orElse(null);
        if (!Objects.isNull(bienResp))
            throw new CustomRuntimeException("Ya existe un bien registrado con el nombre ingresado");

        bien = bienDao.save(bien);
        auditoriaService.registerBienAudit(bien.getId(), convertValue(new EmptyBien()), convertValue(bien));

        return bien;
    }

    /**
     * registrar un lote de nuevos bienes
     *
     * @param bienes - list de objects de tipo Bien a ser guardado
     */
    @Transactional
    public void saveAll(List<Bien> bienes) {
        var nombres = bienes.stream().map(Bien::getNombre).collect(Collectors.toList());
        var bienesList = bienDao.findBienesInNames(nombres);
        if (!bienesList.isEmpty()) throw new CustomRuntimeException("Uno o varios bienes a registrar ya existen!");

        bienes.forEach(bien -> {
            bien = bienDao.save(bien);
            auditoriaService.registerBienAudit(bien.getId(), convertValue(new EmptyBien()), convertValue(bien));
        });
    }

    /**
     * buscar cuantos bienes disponibles existe en cada categoria
     */
    public List<BienesDisponiblesProyection> findBienesDisponiblesByCategory() {
        return bienDao.findBienesDisponiblesByCategory();
    }

    /**
     * buscar cuantos bienes disponibles y dados de baja existe en una categoria
     *
     * @param categoria - id de la categoria para buscar bienes disponibles y dados de baja
     */
    public List<BienesDisponiblesDadosDeBajaProyection> findBienesDisponiblesDadosDeBajaByCategory(Integer categoria) {
        return bienDao.findBienesDisponiblesDadosDeBajaByCategory(categoria);
    }

    /**
     * dar de baja un bien
     *
     * @param ids - ids del o los bienes a dar de baja
     */
    @Transactional
    public void darDeBaja(List<Integer> ids) {
        var bienes = bienDao.findBienesInIds(ids);
        if (bienes.isEmpty()) throw new CustomRuntimeException("No existe el/los bienes a los que desea dar de baja");

        bienes.forEach(bien -> {
            if (bien.getIdEstado() != Constant.DADO_DE_BAJA) {
                Bien newBien = new Bien();
                String preData = convertValue(bien);
                newBien.setId(bien.getId());
                newBien.setNombre(bien.getNombre());
                newBien.setIdCategoria(bien.getIdCategoria());
                newBien.setStock(bien.getStock());

                newBien.setIdEstado(Constant.DADO_DE_BAJA);
                newBien = bienDao.save(newBien);
                auditoriaService.registerBienAudit(newBien.getId(), preData, convertValue(newBien));
            }
        });
    }

    /**
     * convertir un objecto a string
     *
     * @param object - objecto para convertir a string
     */
    private String convertValue(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error transform object to json");
        }

        return "";
    }
}

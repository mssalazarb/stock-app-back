package com.pichincha.stock.service;

import com.pichincha.stock.dao.CategoriaDao;
import com.pichincha.stock.entity.Categoria;
import com.pichincha.stock.exception.CustomRuntimeException;
import com.pichincha.stock.proyection.CategoriaProyection;
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
public class CategoriaService {

    private final CategoriaDao catagoriaDao;

    /**
     * buscar todas las categorias sin referencias
     */
    public List<CategoriaProyection> findAllWithoutDetail() {
        return catagoriaDao.findAllWithoutDetail();
    }

    /**
     * buscar todas las categorias con referencias
     */
    public List<Categoria> findAll() {
        return catagoriaDao.findAll();
    }

    /**
     * buscar una categoria por id
     *
     * @param name - busacar una categoria por su nombre
     */
    public Categoria findByNombre(String name) {
        return catagoriaDao.findByNombre(name).orElse(null);
    }

    /**
     * crear una nueva categoria
     */
    @Transactional
    public Categoria save(Categoria categoria) {
        var category = catagoriaDao.findByNombre(categoria.getNombre()).orElse(null);
        if (!Objects.isNull(category)) {
            throw new CustomRuntimeException("Ya existe una categoría con el nombre ingresado");
        }

        return catagoriaDao.save(categoria);
    }

    /**
     * actualizar una categoria
     */
    @Transactional
    public Categoria update(Categoria categoria) {
        return catagoriaDao.save(categoria);
    }
}

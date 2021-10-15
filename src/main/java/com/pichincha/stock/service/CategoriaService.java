package com.pichincha.stock.service;

import com.pichincha.stock.dao.CatagoriaDao;
import com.pichincha.stock.entity.Categoria;
import com.pichincha.stock.exception.CustomRuntimeException;
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

    private final CatagoriaDao catagoriaDao;

    public List<Categoria> findAll() {
        return catagoriaDao.findAll();
    }

    public Categoria findById(Integer id) {
        return catagoriaDao.findById(id).orElse(null);
    }

    @Transactional
    public Categoria save(Categoria categoria) {
        var category = catagoriaDao.findByNombre(categoria.getNombre()).orElse(null);
        if (!Objects.isNull(category)) {
            throw new CustomRuntimeException("Ya existe una categoría con el nombre ingresado");
        }

        return catagoriaDao.save(categoria);
    }

    @Transactional
    public Categoria update(Categoria categoria) {
        return catagoriaDao.save(categoria);
    }
}

package com.pichincha.stock.service;

import com.pichincha.stock.dao.BienCategoriaDao;
import com.pichincha.stock.entity.BienCategoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BienCategoriaService {

    private final BienCategoriaDao bienCategoriaDao;

    public List<BienCategoria> findAll() {
        return bienCategoriaDao.findAll();
    }
}

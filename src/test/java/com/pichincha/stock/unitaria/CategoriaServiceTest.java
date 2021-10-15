package com.pichincha.stock.unitaria;

import com.pichincha.stock.entity.Categoria;
import com.pichincha.stock.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CategoriaServiceTest {

    @Autowired
    private CategoriaService service;

    @Test
    void findAll() {
        List<Categoria> categorias = service.findAll();

        assert !categorias.isEmpty();
    }

}

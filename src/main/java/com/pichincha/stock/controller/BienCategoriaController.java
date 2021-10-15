package com.pichincha.stock.controller;

import com.pichincha.stock.entity.BienCategoria;
import com.pichincha.stock.service.BienCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * descripcion: Controller para la tabla bienes_categoria
 */
@RestController
@RequestMapping("/bien-categoria")
@RequiredArgsConstructor
public class BienCategoriaController {
    private final BienCategoriaService bienCategoriaService;

    @GetMapping("/all")
    public ResponseEntity<List<BienCategoria>> findAll() {
        var response = bienCategoriaService.findAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

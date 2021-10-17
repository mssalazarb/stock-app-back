package com.pichincha.stock.controller;

import com.pichincha.stock.entity.Categoria;
import com.pichincha.stock.proyection.CategoriaProyection;
import com.pichincha.stock.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author mssalazarb
 * @version 1
 */
@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    /**
     * buscar todas las categorias sin bienes relacionados
     */
    @GetMapping("/all")
    public ResponseEntity<List<CategoriaProyection>> findAllWithoutDetail() {
        var response = categoriaService.findAllWithoutDetail();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * buscar todas las categorias con bienes relacionados
     */
    @GetMapping("/all-detail")
    public ResponseEntity<List<Categoria>> findAll() {
        var response = categoriaService.findAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * buscar una categoria por id
     *
     * @param name - busacar una categoria por su nombre
     */
    @GetMapping
    public ResponseEntity<Categoria> findByNombre(@RequestParam("name") String name) {
        var response = categoriaService.findByNombre(name);
        if (Objects.isNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * crear una nueva categoria
     */
    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
        var response = categoriaService.save(categoria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * actualizar una categoria
     */
    @PutMapping
    public ResponseEntity<Categoria> update(@RequestBody Categoria categoria) {
        var response = categoriaService.update(categoria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

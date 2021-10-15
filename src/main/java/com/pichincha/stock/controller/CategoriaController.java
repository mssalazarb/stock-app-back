package com.pichincha.stock.controller;

import com.pichincha.stock.entity.Categoria;
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
 * <p>
 * descripcion: Controller para la tabla categoria
 */
@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping("/all")
    public ResponseEntity<List<Categoria>> findAll() {
        var response = categoriaService.findAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Categoria> findById(@RequestParam("id") Integer id) {
        var response = categoriaService.findById(id);
        if (Objects.isNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
        var response = categoriaService.save(categoria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Categoria> update(@RequestBody Categoria categoria) {
        var response = categoriaService.update(categoria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

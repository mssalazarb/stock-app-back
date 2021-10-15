package com.pichincha.stock.controller;

import com.pichincha.stock.entity.Bien;
import com.pichincha.stock.proyection.BienProyection;
import com.pichincha.stock.service.BienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/bien")
@RequiredArgsConstructor
public class BienController {
    private final BienService bienService;

    @GetMapping("/all")
    public ResponseEntity<List<BienProyection>> findAll() {
        var response = bienService.findAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BienProyection> findById(@RequestParam("id") Integer id) {
        var response = bienService.findById(id);
        if (Objects.isNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Bien> findByIdDetail(@RequestParam("id") Integer id) {
        var response = bienService.findByIdDetail(id);
        if (Objects.isNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bien> save(@RequestBody Bien bien) {
        var response = bienService.save(bien);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/disponibles-categoria")
    public ResponseEntity<Integer> findBienesByCategory(@RequestParam("categoria") Integer id) {
        var response = bienService.findBienesByCategory(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package com.pichincha.stock.controller;

import com.pichincha.stock.entity.Bien;
import com.pichincha.stock.proyection.BienProyection;
import com.pichincha.stock.proyection.BienesDisponiblesDadosDeBajaProyection;
import com.pichincha.stock.proyection.BienesDisponiblesProyection;
import com.pichincha.stock.service.BienService;
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
@RequestMapping("/bien")
@RequiredArgsConstructor
public class BienController {
    private final BienService bienService;

    /**
     * buscar todos los bienes con el detalle de su respectivo estado
     */
    @GetMapping("/all-estado")
    public ResponseEntity<List<BienProyection>> findAllBienesStatus() {
        var response = bienService.findAllBienesStatus();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * buscar un bien por nombre
     *
     * @param name - nombre del bien
     */
    @GetMapping
    public ResponseEntity<BienProyection> findByNameBien(@RequestParam("name") String name) {
        var response = bienService.findByNameBien(name);
        if (Objects.isNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * buscar un bien con su detalle por nombre
     *
     * @param nombre - nombre del bien
     */
    @GetMapping("/detail")
    public ResponseEntity<Bien> findByNameDetail(@RequestParam("name") String nombre) {
        var response = bienService.findByNameDetail(nombre);
        if (Objects.isNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * registrar un nuevo bien
     *
     * @param bien - object de tipo Bien a ser guardado
     */
    @PostMapping
    public ResponseEntity<Bien> save(@RequestBody Bien bien) {
        var response = bienService.save(bien);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * buscar cuantos bienes disponibles existe en cada categoria
     */
    @GetMapping("/disponibles")
    public ResponseEntity<List<BienesDisponiblesProyection>> findBienesDisponiblesByCategory() {
        var response = bienService.findBienesDisponiblesByCategory();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * buscar cuantos bienes disponibles y dados de baja existe en una categoria
     *
     * @param categoria - id de la categoria para buscar bienes disponibles y dados de baja
     */
    @GetMapping("/disponibles-categoria")
    public ResponseEntity<List<BienesDisponiblesDadosDeBajaProyection>> findBienesDisponiblesDadosDeBajaByCategory(@RequestParam Integer categoria) {
        var response = bienService.findBienesDisponiblesDadosDeBajaByCategory(categoria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * registrar un lote de nuevos bienes
     *
     * @param bienes - list de objects de tipo Bien a ser guardado
     */
    @PostMapping("/batch")
    public ResponseEntity<String> saveAll(@RequestBody List<Bien> bienes) {
        bienService.saveAll(bienes);
        return new ResponseEntity<>("Bienes registrados correctamente", HttpStatus.OK);
    }

    /**
     * dar de baja un bien
     *
     * @param ids - ids del o los bienes a dar de baja
     */
    @PutMapping("/withdrawal")
    public ResponseEntity<String> darDeBaja(@RequestBody List<Integer> ids) {
        bienService.darDeBaja(ids);
        return new ResponseEntity<>("Los bienes se dieron de baja correctamente", HttpStatus.OK);
    }
}

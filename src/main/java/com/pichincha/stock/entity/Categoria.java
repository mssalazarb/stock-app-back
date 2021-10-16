package com.pichincha.stock.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author mssalazarb
 * @version 1
 *
 * Tabla que controla las categorias a la que puede pertenecer un bien
 */
@Entity
@Table(name = "categorias")
@Data
public class Categoria implements Serializable {
    private static final long serialVersionUID = 351968602457016128L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<Bien> bienes;
}

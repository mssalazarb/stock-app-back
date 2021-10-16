package com.pichincha.stock.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mssalazarb
 * @version 1
 * <p>
 * Tabla que controla los distintos estados en los que puede estar un bien
 */
@Entity
@Table(name = "estado_bienes")
@Data
public class EstadoBien implements Serializable {
    private static final long serialVersionUID = 351968602457016128L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
}

package com.pichincha.stock.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mssalazarb
 * @version 1
 */
@Entity
@Table(name = "bienes")
@Data
public class Bien implements Serializable {
    private static final long serialVersionUID = 351968602457016128L;

    @Id
    private Integer id;

    private String nombre;
    private Integer stock;
    private Integer estado;

    @Column(name = "id_tipo")
    private Integer idTipo;

    @JoinColumn(name = "id_tipo", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tipo tipo;
}

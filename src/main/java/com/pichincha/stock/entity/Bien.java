package com.pichincha.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mssalazarb
 * @version 1
 *
 * Tabla que controla los bienes y su stock existente
 */
@Entity
@Table(name = "bienes")
@Data
public class Bien implements Serializable {
    private static final long serialVersionUID = 351968602457016128L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Integer stock;

    @Column(name = "id_estado")
    private Integer idEstado;

    @JoinColumn(name = "id_estado", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private EstadoBien estado;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @JoinColumn(name = "id_categoria", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    @JsonIgnore
    private Categoria categoria;

    @Transient
    private String estadoBien;
}

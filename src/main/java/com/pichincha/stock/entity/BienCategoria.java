package com.pichincha.stock.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bienes_categorias")
@Data
public class BienCategoria implements Serializable {
    private static final long serialVersionUID = 351968602457016128L;

    @Id
    private Integer id;

    @Column(name = "id_bien")
    private Integer idBien;

    @JoinColumn(name = "id_bien", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Bien bien;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @JoinColumn(name = "id_categoria", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Categoria categoria;
}

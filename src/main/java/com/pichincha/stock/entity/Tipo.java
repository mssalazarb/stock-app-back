package com.pichincha.stock.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author mssalazarb
 * @version 1
 */
@Entity
@Table(name = "tipo")
@Data
public class Tipo implements Serializable {
    private static final long serialVersionUID = 351968602457016128L;

    @Id
    private Integer id;

    private String descripcion;
}

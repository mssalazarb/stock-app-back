package com.pichincha.stock.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tipo_transaccion_bienes")
@Data
public class TipoTransaccion implements Serializable {
    private static final long serialVersionUID = 351968602457016128L;

    @Id
    private Integer id;

    private String descripcion;
}

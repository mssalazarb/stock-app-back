package com.pichincha.stock.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

/**
 * @author mssalazarb
 * @version 1
 */
@Entity
@Table(name = "auditoria_bienes")
@Data
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class AuditoriaBien {
    private static final long serialVersionUID = 351968602457016128L;

    @Id
    private Integer id;

    @Column(name = "id_bien")
    private Integer idBien;

    @JoinColumn(name = "id_bien", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Bien bien;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Bien preData;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Bien postData;

    @Column(name = "id_transaccion")
    private Integer idTipoTransaccion;

    @JoinColumn(name = "id_transaccion", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoTransaccion tipoTransaccion;
}

package com.pichincha.stock.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author mssalazarb
 * @version 1
 *
 * Tabla que almacena las transacciones de los bienes
 */
@Entity
@Table(name = "auditoria_bienes")
@Data
@TypeDef(name = "json", typeClass = JsonType.class)
public class AuditoriaBien {
    private static final long serialVersionUID = 351968602457016128L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_bien")
    private Integer idBien;

    @JoinColumn(name = "id_bien", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Bien bien;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String preData;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String postData;

    private LocalDateTime fecha;
}

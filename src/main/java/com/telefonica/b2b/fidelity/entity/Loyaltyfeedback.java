package com.telefonica.b2b.fidelity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "FEEDBACK_LOYALTY")
@Data
public class Loyaltyfeedback {
    
    @Id
    @Column(name = "ID")
    private String id;
    
    @Column(name = "ABONADO")
    private String abonado;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "COD_ERROR")
    private String cod_error;

    @Column(name = "DESC_ERROR")
    private String descerror;

    @Column(name = "BUSINESS_LINE_TYPE")
    private String businesslinetype;
    
}

package com.telefonica.b2b.fidelity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "LOYALTY_CAMPAIGN")
@Data
public class Loyaltycampaign {
    
    @Id
    @Column(name = "ID")
    private String id;
    
    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "BUSINESS_LINE_TYPE")
    private String businesslinetype;

}

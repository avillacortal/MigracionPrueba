package com.telefonica.b2b.fidelity.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CUSTOMER_FEEDBACK_RESPONSE")
@Data
public class CustomerFeedbackResponse {

    @Column(name = "SERVICEID")
    private String serviceid;

    @Id
    @Column(name = "RESPONSE_TRACKING_CD")
    private String responseTrackingCd;

    @Column(name = "SUBSCRIBER_ID")
    private String subscriberid;

    @Column(name = "CAMPAIGN_ID")
    private String campaignid;

    @Column(name = "COD_RESUL_LLAM")
    private String codresulllam;

    @Column(name = "LINEOFBUSSINESSTYPE")
    private String lineofbussinesstype;

    @Column(name = "COD_MOTIVO")
    private String codMotivo;

    @Column(name = "COD_SUBMOTIVO")
    private String codSubMotivo;

    @Column(name = "DESC_OFERTA")
    private String descOferta;

    @Column(name = "COD_OFERTA")
    private String codOferta;

    @Column(name = "ID_OFERTA")
    private String idOferta;

    @Column(name = "CHANNEL")
    private String channel;

    @Column(name = "ID_CTI")
    private String idCti;

    @Column(name = "ID_CALL")
    private String idCall;

    @Column(name = "INTERACTION_ID")
    private String interactionId;

    @Column(name = "FECHA_HORA_FEEDBACK")
    private Date datetime_feedback;

    @Column(name = "FECHA_PROCESO")
    private Date processDate;

    @Column(name = "CONTACTMEDIUMVALUE")
    private String contactMediumValue;

    @Column(name = "CONTACTMEDIUMTYPE")
    private String contactMediumType;

    @Column(name = "CREATION_USER")
    private String creationUser;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "CREATION_IP")
    private String creationIp;
}

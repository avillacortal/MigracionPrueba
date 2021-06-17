package com.telefonica.b2b.fidelity.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "rtdm_offers")
public class RtdmOffers {
    @Id
    @Column(name = "CAMPAIGN_ID")
    private String campaignId;

    @Column(name = "CAMPAIGN_DESC")
    private String campaignDesc;

    @Column(name = "CAMPAIGN_TYPE")
    private String campaignType;

    @Column(name = "OFFER_CODE")
    private String offerCode;

    @Column(name = "OFFER_ID")
    private String offerId;

    @Column(name = "OFFER_DESC")
    private String offerDesc;

    @Column(name = "VALIDITY_START_DATE")
    private Date validityStartDate;

    @Column(name = "VALIDITY_END_DATE")
    private Date validityEndDate;

    @Column(name = "MAX_CONTACT")
    private String maxContact;

    @Column(name = "CONDITION")
    private String condition;

    @Column(name = "ATTRIBUTES")
    private String attributes;

    @Column(name = "BUSINESS_LINE_TYPE")
    private String bussinesLineType;

}

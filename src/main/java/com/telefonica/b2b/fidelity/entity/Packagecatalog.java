package com.telefonica.b2b.fidelity.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.telefonica.b2b.fidelity.commons.Constant;
import com.telefonica.b2b.fidelity.commons.Util;
import com.telefonica.b2b.fidelity.entity.strategy.ICatalogStrategy;
import com.telefonica.b2b.fidelity.type.OfferType;

import lombok.Data;

@Entity
@Table(name = "RTDM_PACKAGECATALOG")
@Data
public class Packagecatalog implements Serializable, ICatalogStrategy {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "package_description")
    private String offerDescription;
    @Column(name = "price")
    private String price;
    @Column(name = "com_pack_desc_ussd")
    private String ussdDescription;
    @Column(name = "effective_date")
    private String effectiveDate;
    @Column(name = "expiration_date")
    private String expirationDate;
    @Column(name = "sales_effective_date_pack")
    private String salesEffectiveDate;
    @Column(name = "sales_expiration_date_pack")
    private String salesExpirationDate;
    @Column(name = "sales_type")
    private String salesType;
    @Column(name = "package_duo")
    private String packageDuo;
    @Column(name = "keyword")
    private String keyword;
    @Column(name = "recurrence_period")
    private String recurrencePeriod;
    @Column(name = "package_type")
    private String packageType;
    @Column(name = "delivery_type")
    private String deliveryType;
    @Column(name = "com_pack_desc_self_serv")
    private String mcssDescription;
    @Column(name = "amount_package_type")
    private String amountPackageType;
    @Column(name = "period")
    private String period;

    public void buildOfferType(OfferType offerType) {
	offerType.setOfferId(id);
	offerType.setOfferDescription(offerDescription);
	offerType.setOfferPrice(price);
	offerType.addOfferAdditionalInformationItem(
		Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_USSDDESCRIPTION, ussdDescription));
	offerType
		.addOfferAdditionalInformationItem(Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_EFFECTIVEDATE, effectiveDate));
	offerType.addOfferAdditionalInformationItem(
		Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_EXPIRATIONDATE, expirationDate));
	offerType.addOfferAdditionalInformationItem(
		Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_SALESEFFECTIVEDATE, salesEffectiveDate));
	offerType.addOfferAdditionalInformationItem(
		Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_SALESEXPIRATIONDATE, salesExpirationDate));
	offerType.addOfferAdditionalInformationItem(Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_SALESTYPE, salesType));
	offerType.addOfferAdditionalInformationItem(Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_PACKAGEDUO, packageDuo));
	offerType.addOfferAdditionalInformationItem(Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_KEYWORD, keyword));
	offerType.addOfferAdditionalInformationItem(
		Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_RECURRENCEPERIOD, recurrencePeriod));
	offerType.addOfferAdditionalInformationItem(Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_PACKAGETYPE, packageType));
	offerType.addOfferAdditionalInformationItem(Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_DELIVERYTYPE, deliveryType));
	offerType.addOfferAdditionalInformationItem(
		Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_MCSSDESCRIPTION, mcssDescription));
	offerType.addOfferAdditionalInformationItem(
		Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_AMOUNTPACKAGETYPE, amountPackageType));
	offerType.addOfferAdditionalInformationItem(Util.buildAdditionalInformation(Constant.OFFER_ADDITIONAL_PERIOD, period));
    }

}

package com.telefonica.b2b.fidelity.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.telefonica.b2b.fidelity.commons.Constant;
import com.telefonica.b2b.fidelity.commons.MessageProp;
import com.telefonica.b2b.fidelity.commons.Util;
import com.telefonica.b2b.fidelity.entity.Loyaltycampaign;
import com.telefonica.b2b.fidelity.entity.Loyaltyfeedback;
import com.telefonica.b2b.fidelity.entity.Packagecatalog;
import com.telefonica.b2b.fidelity.entity.RtdmOffers;
import com.telefonica.b2b.fidelity.entity.strategy.Catalog;
import com.telefonica.b2b.fidelity.enums.CategoryEnum;
import com.telefonica.b2b.fidelity.enums.ProductMobileEnum;
import com.telefonica.b2b.fidelity.jdbc.SubscriberMobileDAO;
import com.telefonica.b2b.fidelity.pojo.Subscriber;
import com.telefonica.b2b.fidelity.repository.CustomerFeedbackRepository;
import com.telefonica.b2b.fidelity.repository.LoyaltycampaignRepository;
import com.telefonica.b2b.fidelity.repository.LoyaltyfeedbackRepository;
import com.telefonica.b2b.fidelity.repository.PackagecatalogRepository;
import com.telefonica.b2b.fidelity.repository.RtdmOffersRepository;
import com.telefonica.b2b.fidelity.type.CampaignType;
import com.telefonica.b2b.fidelity.type.OfferType;

@Service
public class MobileBusiness {

    @Autowired
    private SubscriberMobileDAO	       subscriberMobileDAO;
    @Autowired
    private CustomerFeedbackRepository customerFeedbackRepo;
    @Autowired
    private PackagecatalogRepository   packageRepo;
    @Autowired
    private RtdmOffersRepository       rtdmOffersRepository;
    @Autowired
    private MessageProp		       prop;
    @Autowired
    private LoyaltycampaignRepository  loyaltycampaignRepository;
    @Autowired
    private LoyaltyfeedbackRepository  loyaltyfeedbackRepository;

    public List<CampaignType> getCampaigns(String nationalIdType, String nationalId, String salesChannel) {
	List<Subscriber> lstSubscriber = this.getSubscribers(nationalIdType, nationalId);
	lstSubscriber = this.filterSubscribers(lstSubscriber);
	return this.buildResponse(lstSubscriber, salesChannel);
    }

    private List<Subscriber> getSubscribers(String nationalIdType, String nationalId) {
	return subscriberMobileDAO.getCustomerByNationalId(nationalIdType, nationalId);
    }

    private List<Subscriber> filterSubscribers(List<Subscriber> lstSubscriber) {
	if (!CollectionUtils.isEmpty(lstSubscriber)) {
	    lstSubscriber = this.filterStatusActive(lstSubscriber);
	    lstSubscriber = this.filterPostpaid(lstSubscriber);
	    lstSubscriber = this.filterNotBam(lstSubscriber);
	    lstSubscriber = this.filterNotBlindage(lstSubscriber);
	}
	lstSubscriber.forEach(s -> this.insertLoyaltyCampaign(s));
	return lstSubscriber;
    }

    private List<CampaignType> buildResponse(List<Subscriber> lstSubscriber, String salesChannel) {
	RtdmOffers parameters = rtdmOffersRepository
		.findByCampaignTypeAndBussinesLineType(Constant.RTDMOFFERS_CAMPAIGNTYPE, Constant.CAMPAIGN_MOBILE_RTDM_OFFERS).get(0);

	List<CampaignType> lstCampaign = new ArrayList<>();
	lstSubscriber.forEach(s -> lstCampaign.add(this.buildCampaign(s, parameters, salesChannel)));
	return lstCampaign;
    }

    private List<Subscriber> filterStatusActive(List<Subscriber> lstSubscriber) {
	return lstSubscriber.stream().filter(s -> {
	    boolean value =  Util.subscriberStateActive().contains(s.getSubscriberState())
		    && Util.statusActive().contains(s.getPrincipalcomponent().getStatus().toUpperCase());
	    this.insertLoyaltyFeedback(value, s, Constant.BE_1380, prop.obtainDetail(Constant.BE_1380));
	    return value;
	}).collect(Collectors.toList());

    }

    private List<Subscriber> filterPostpaid(List<Subscriber> lstSubscriber) {
	return lstSubscriber.stream().filter(s -> {
	    boolean value = CategoryEnum.POSTPAID.equals(CategoryEnum.fromValue(s.getCategory()));
	    System.out.print("POSTPAGO");
	    this.insertLoyaltyFeedback(value, s, Constant.BE_1381, prop.obtainDetail(Constant.BE_1381));
	    return value;
	}).collect(Collectors.toList());
    }

    private List<Subscriber> filterNotBam(List<Subscriber> lstSubscriber) {
	return lstSubscriber.stream().filter(s -> {
	    boolean value = !ProductMobileEnum.BAM.equals(ProductMobileEnum.fromValueBD(s.getPrincipalcomponent().getName()));
	    System.out.print("BAM + ");
	    System.out.print(s.getPrincipalcomponent().getName());
	    this.insertLoyaltyFeedback(value, s, Constant.BE_1382, prop.obtainDetail(Constant.BE_1382));
	    return value;
	}).collect(Collectors.toList());
    }

    private List<Subscriber> filterNotBlindage(List<Subscriber> lstSubscriber) {
	return lstSubscriber.stream().filter(s -> {
	    boolean value = CollectionUtils
		    .isEmpty(this.customerFeedbackRepo.findByServiceidAndCodresulllam(s.getMsisdn(), Constant.COD_RESUL_LLAM));
	    System.out.print("ULTIMO FILTRO");
	    this.insertLoyaltyFeedback(value, s, Constant.BE_1383, prop.obtainDetail(Constant.BE_1383));
	    return value;
	}).collect(Collectors.toList());
    }
    // lstSubscriber SI ENTRAN

    private CampaignType buildCampaign(Subscriber subscriber, RtdmOffers parameters, String salesChannel) {
	CampaignType campaigntype = new CampaignType();
	campaigntype.setCampaignId(Constant.CAMPAIGNS_CAMPAIGNID);
	campaigntype.setCampaignType(Constant.CAMPAIGNS_CAMPAIGNTYPE);
	campaigntype.setResponseTrackingCD(Util.generateResponseTracking());
	campaigntype.setCampaignName(Constant.CAMPAIGNS_CAMPAIGNNAME);
	campaigntype.setPriority(Constant.CAMPAIGNS_PRIORITY);
	campaigntype.addCampaignAdditionalInformationItem(
		Util.buildAdditionalInformation(Constant.CAMPAIGN_ADDITIONAL_PHONENUMBER, subscriber.getMsisdn()));
	campaigntype.addCampaignAdditionalInformationItem(Util.buildAdditionalInformation(Constant.CAMPAIGN_ADDITIONAL_DATESTARTKEY,
		Util.getDate(parameters.getValidityStartDate())));
	campaigntype.addCampaignAdditionalInformationItem(
		Util.buildAdditionalInformation(Constant.CAMPAIGN_ADDITIONAL_DATEENDKEY, Util.getDate(parameters.getValidityEndDate())));
	campaigntype.addOffersItem(this.buildOffers(parameters, salesChannel));
	return campaigntype;
    }

    private OfferType buildOffers(RtdmOffers parameters, String salesChannel) {
	Packagecatalog packagecatalog = this.packageRepo.findById(parameters.getOfferId()).stream()
		.filter(el -> el.getId().equals(parameters.getOfferId())).findAny().orElse(null);
	if (packagecatalog == null) {
	    return null;
	}
	Catalog catalog = new Catalog(packagecatalog);
	OfferType offerType = new OfferType();
	offerType.setChannel(salesChannel);
	catalog.buildOfferType(offerType);
	offerType.setOfferDescription(parameters.getCampaignDesc());
	return offerType;
    }

    private void insertLoyaltyFeedback(boolean validateClientFeedback, Subscriber subscriber, String codeError, String descError) {
	if (!validateClientFeedback) {
	    Loyaltyfeedback loyaltyfeedback = new Loyaltyfeedback();
	    loyaltyfeedback.setId(subscriber.getSubscriberid());
	    loyaltyfeedback.setAbonado(subscriber.getSubscriberid());
	    loyaltyfeedback.setBusinesslinetype(subscriber.getCategory());
	    loyaltyfeedback.setCod_error(codeError);
	    loyaltyfeedback.setDescerror(descError);
	    loyaltyfeedback.setTelefono(subscriber.getMsisdn());
	    loyaltyfeedbackRepository.save(loyaltyfeedback);
	}
    }

    private void insertLoyaltyCampaign(Subscriber subscriber) {
	Loyaltycampaign loyaltycampaign = new Loyaltycampaign();
	loyaltycampaign.setId(subscriber.getSubscriberid());
	loyaltycampaign.setTelefono(subscriber.getMsisdn());
	loyaltycampaign.setBusinesslinetype(subscriber.getCategory());
	loyaltycampaignRepository.save(loyaltycampaign);
    }
}

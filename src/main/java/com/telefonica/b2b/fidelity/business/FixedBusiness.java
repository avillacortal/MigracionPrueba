package com.telefonica.b2b.fidelity.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.telefonica.b2b.fidelity.commons.Constant;
import com.telefonica.b2b.fidelity.commons.MessageProp;
import com.telefonica.b2b.fidelity.commons.Util;
import com.telefonica.b2b.fidelity.entity.CustomerFeedbackResponse;
import com.telefonica.b2b.fidelity.entity.Loyaltycampaign;
import com.telefonica.b2b.fidelity.entity.Loyaltyfeedback;
import com.telefonica.b2b.fidelity.entity.RtdmOffers;
import com.telefonica.b2b.fidelity.jdbc.SubscriberFixedDAO;
import com.telefonica.b2b.fidelity.pojo.Subscriber;
import com.telefonica.b2b.fidelity.repository.CustomerFeedbackRepository;
import com.telefonica.b2b.fidelity.repository.LoyaltycampaignRepository;
import com.telefonica.b2b.fidelity.repository.LoyaltyfeedbackRepository;
import com.telefonica.b2b.fidelity.repository.RtdmOffersRepository;
import com.telefonica.b2b.fidelity.type.CampaignType;
import com.telefonica.b2b.fidelity.type.OfferType;

@Service

public class FixedBusiness {

    @Autowired
    private SubscriberFixedDAO	       subscriberFixedDAO;
    @Autowired
    private RtdmOffersRepository       rtdmOffersRepo;
    @Autowired
    private CustomerFeedbackRepository customerFeedbackRepo;
    @Autowired
    private MessageProp		       prop;
    @Autowired
    private LoyaltycampaignRepository  loyaltycampaignRepository;
    @Autowired
    private LoyaltyfeedbackRepository  loyaltyfeedbackRepository;

    public List<CampaignType> getCampaigns(String nationalIdType, String nationalId) {
	List<Subscriber> lstSubscriber = this.getSubscribers(nationalIdType, nationalId);
	lstSubscriber = this.filterSubscribers(lstSubscriber);
	return this.buildResponse(lstSubscriber);
    }

    private List<Subscriber> getSubscribers(String nationalIdType, String nationalId) {
	return subscriberFixedDAO.getCustomerByNationalId(nationalIdType, nationalId);
    }

    private List<Subscriber> filterSubscribers(List<Subscriber> lstSubscriber) {
	if (!CollectionUtils.isEmpty(lstSubscriber)) {
	    lstSubscriber = this.filterStatusActive(lstSubscriber);
	    lstSubscriber = this.filterResidential(lstSubscriber);
	    lstSubscriber = this.filterSpeed(lstSubscriber);
	    lstSubscriber = this.filterValidity(lstSubscriber);
	}
	lstSubscriber.forEach(s -> this.insertLoyaltyCampaignFixed(s));
	return lstSubscriber;
    }

    private List<CampaignType> buildResponse(List<Subscriber> lstSubscriber) {
	List<CampaignType> lstCampaign = new ArrayList<>();
	lstSubscriber.forEach(s -> lstCampaign.add(this.buildCampaign(s)));
	return lstCampaign;
    }

    private List<Subscriber> filterStatusActive(List<Subscriber> lstSubscriber) {
	return lstSubscriber.stream().filter(s -> {
	    boolean value = Util.statusActive().contains(s.getSubscriberState().toUpperCase())
		    && Util.statusActive().contains(s.getPrincipalcomponent().getStatus().toUpperCase());
	    this.insertLoyaltyFeedbackFixed(value, s, Constant.BE_1380, prop.obtainDetail(Constant.BE_1380));
	    return value;
	}).collect(Collectors.toList());
    }

    private List<Subscriber> filterResidential(List<Subscriber> lstSubscriber) {
	return lstSubscriber.stream().filter(s -> {
	    boolean value = Arrays.asList(Constant.RESIDENTIAL, Constant.RESIDENTIAL2).contains(s.getCategory());
	    this.insertLoyaltyFeedbackFixed(value, s, Constant.BE_1384, prop.obtainDetail(Constant.BE_1384));
	    return value;
	}).collect(Collectors.toList());
    }

    private List<Subscriber> filterSpeed(List<Subscriber> lstSubscriber) {
	return lstSubscriber.stream().filter(s -> {
	    boolean value = s.getPrincipalcomponent().getPsCampana().getVelocidad() < Constant.SPEED_VALUE;
	    this.insertLoyaltyFeedbackFixed(value, s, Constant.BE_1385, prop.obtainDetail(Constant.BE_1385));
	    return value;
	}).collect(Collectors.toList());
    }

    private List<Subscriber> filterValidity(List<Subscriber> lstSubscriber) {
	return lstSubscriber.stream().filter(s -> {
	    RtdmOffers parameters = rtdmOffersRepo
		    .findByCampaignTypeAndBussinesLineType(Constant.CAMPAIGN_TYPE, Constant.CAMPAIGN_FIXED_RTDM_OFFERS).stream()
		    .filter(p -> Util.getMinConditionFixed(p.getCondition()) <= s.getPrincipalcomponent().getPsCampana().getVelocidad()
			    && Util.getMaxConditionFixed(p.getCondition()) >= s.getPrincipalcomponent().getPsCampana().getVelocidad())
		    .findFirst().orElse(null);
	    if (parameters == null || Util.getDateTimeToMidnight(new Date()).compareTo(parameters.getValidityEndDate()) > 0) {
		this.insertLoyaltyFeedbackFixed(false, s, Constant.BE_1386, prop.obtainDetail(Constant.BE_1386));
		return false;
	    }
	    s.setParameters(parameters);

	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.add(Calendar.DAY_OF_YEAR, -Integer.parseInt(parameters.getMaxContact()));
	    List<CustomerFeedbackResponse> lstAnswers = this.customerFeedbackRepo
		    .findByServiceidAndCodresulllam(s.getMsisdn(), Constant.COD_RESUL_LLAM).stream()
		    .filter(p -> p.getProcessDate().compareTo(calendar.getTime()) >= 0).collect(Collectors.toList());
	    return lstAnswers.isEmpty();
	}).collect(Collectors.toList());
    }

    private CampaignType buildCampaign(Subscriber subscriber) {
	CampaignType campaigntype = new CampaignType();
	campaigntype.setCampaignId(Constant.CAMPAIGNS_CAMPAIGNID);
	campaigntype.setCampaignType(Constant.CAMPAIGNS_CAMPAIGNTYPE);
	campaigntype.setResponseTrackingCD(Util.generateResponseTracking());
	campaigntype.setCampaignName(Constant.CAMPAIGNS_CAMPAIGNNAME);
	campaigntype.setPriority(Constant.CAMPAIGNS_PRIORITY);
	campaigntype.setCampaignType(subscriber.getParameters().getCampaignType());
	campaigntype.setCampaignName(subscriber.getParameters().getCampaignDesc());
	campaigntype.addCampaignAdditionalInformationItem(
		Util.buildAdditionalInformation(Constant.CAMPAIGN_ADDITIONAL_PHONENUMBER, subscriber.getMsisdn()));
	campaigntype.addCampaignAdditionalInformationItem(Util.buildAdditionalInformation(Constant.CAMPAIGN_ADDITIONAL_DATESTARTKEY,
		Util.getDate(subscriber.getParameters().getValidityStartDate())));
	campaigntype.addCampaignAdditionalInformationItem(Util.buildAdditionalInformation(Constant.CAMPAIGN_ADDITIONAL_DATEENDKEY,
		Util.getDate(subscriber.getParameters().getValidityEndDate())));
	campaigntype.addOffersItem(this.buildOffer(subscriber.getParameters()));
	return campaigntype;
    }

    private OfferType buildOffer(RtdmOffers parameters) {
	OfferType offerType = new OfferType();
	if (StringUtils.isNotBlank(parameters.getOfferCode())) {
	    offerType.setChannel(Constant.OFFERS_CHANNEL);
	    offerType.setOfferId(parameters.getOfferCode());
	    offerType.setOfferPrice(Constant.OFFERS_OFFERPRICE);
	    offerType.setOfferDescription(parameters.getOfferDesc());
	}
	return offerType;
    }

    private void insertLoyaltyFeedbackFixed(boolean validateClientFeedback, Subscriber subscriber, String codeError, String descError) {
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

    private void insertLoyaltyCampaignFixed(Subscriber subscriber) {
	Loyaltycampaign loyaltycampaign = new Loyaltycampaign();
	loyaltycampaign.setId(subscriber.getSubscriberid());
	loyaltycampaign.setTelefono(subscriber.getMsisdn());
	loyaltycampaign.setBusinesslinetype(subscriber.getCategory());
	loyaltycampaignRepository.save(loyaltycampaign);
    }

}

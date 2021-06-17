package com.telefonica.b2b.fidelity.type;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CampaignType {
    private String			    campaignId;
    private String			    campaignType;
    private String			    campaignSubType;
    private String			    responseTrackingCD;
    private String			    campaignName;
    private String			    priority;
    private String			    campaignImg;
    private FeedbackType		    feedback;
    private List<AdditionalInformationType> campaignAdditionalInformation;
    private List<OfferType>		    offers;

    public CampaignType addCampaignAdditionalInformationItem(AdditionalInformationType campaignAdditionalInformationItem) {
	if (campaignAdditionalInformationItem == null) {
	    return this;
	}
	if (this.campaignAdditionalInformation == null) {
	    this.campaignAdditionalInformation = new ArrayList<>();
	}
	this.campaignAdditionalInformation.add(campaignAdditionalInformationItem);
	return this;
    }

    public CampaignType addOffersItem(OfferType offersItem) {
	if (offersItem == null) {
	    return this;
	}
	if (this.offers == null) {
	    this.offers = new ArrayList<>();
	}
	this.offers.add(offersItem);
	return this;
    }
    
}

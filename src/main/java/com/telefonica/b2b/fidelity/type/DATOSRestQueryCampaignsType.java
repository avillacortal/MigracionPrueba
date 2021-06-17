package com.telefonica.b2b.fidelity.type;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DATOSRestQueryCampaignsType {
    private String		  subscriberId;
    private List<CampaignType>	  campaigns;
    private ServiceIdentifierType serviceIdentifier;
    private String		  resultCode;
    private String		  desc;
    private OfferType		  offer;

    public DATOSRestQueryCampaignsType addCampaignsItem(CampaignType campaignsItem) {
	if (this.campaigns == null) {
	    this.campaigns = new ArrayList<>();
	}
	this.campaigns.add(campaignsItem);
	return this;
    }
    
}

package com.telefonica.b2b.fidelity.type;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OfferType {
    private String			    offerId;
    private String			    offerDescription;
    private String			    offerPrice;
    private String			    channel;
    private List<AdditionalInformationType> offerAdditionalInformation = null;

    public OfferType addOfferAdditionalInformationItem(AdditionalInformationType offerAdditionalInformationItem) {
	if (offerAdditionalInformationItem == null) {
	    return this;
	}
	if (this.offerAdditionalInformation == null) {
	    this.offerAdditionalInformation = new ArrayList<>();
	}
	this.offerAdditionalInformation.add(offerAdditionalInformationItem);
	return this;
    }

}

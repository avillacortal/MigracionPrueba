package com.telefonica.b2b.fidelity.service;

import com.telefonica.b2b.fidelity.type.DATOSRestQueryCampaignsType;

public interface IB2bFidelityService {

    public DATOSRestQueryCampaignsType getCampaigns(String nationalIdType, String nationalId, String salesChannel,
	    String lineOfBusinessType);

}

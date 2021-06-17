package com.telefonica.b2b.fidelity.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.telefonica.b2b.fidelity.business.FixedBusiness;
import com.telefonica.b2b.fidelity.business.MobileBusiness;
import com.telefonica.b2b.fidelity.commons.Constant;
import com.telefonica.b2b.fidelity.exception.BusinessException;
import com.telefonica.b2b.fidelity.type.CampaignType;
import com.telefonica.b2b.fidelity.type.DATOSRestQueryCampaignsType;

@Service
public class B2bFidelityService implements IB2bFidelityService {

    @Autowired
    private MobileBusiness mobileBusiness;
    @Autowired
    private FixedBusiness  fixedBusiness;

    @Override
    public DATOSRestQueryCampaignsType getCampaigns(String nationalIdType, String nationalId, String salesChannel,
	    String lineOfBusinessType) {
	DATOSRestQueryCampaignsType response = new DATOSRestQueryCampaignsType();
	List<CampaignType> lstCampaigns = new ArrayList<>();

	if (lineOfBusinessType.equals(Constant.VALUE_WRLS)) {

	    lstCampaigns.addAll(mobileBusiness.getCampaigns(nationalIdType, nationalId, salesChannel));

	} else if (lineOfBusinessType.equals(Constant.VALUE_VOICE) || (lineOfBusinessType.equals(Constant.VALUE_TV))) {

	    lstCampaigns.addAll(fixedBusiness.getCampaigns(nationalIdType, nationalId));
	}

	response.setCampaigns(lstCampaigns);

	if (CollectionUtils.isEmpty(lstCampaigns)) {
	    throw new BusinessException(Constant.BE_1021);
	}
	return response;

    }

}

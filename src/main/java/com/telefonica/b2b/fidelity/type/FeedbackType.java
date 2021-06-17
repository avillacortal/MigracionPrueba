package com.telefonica.b2b.fidelity.type;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FeedbackType {
    private String		       campaignId;
    private String		       responseTrackingCD;
    private String		       campaignResultCode;
    private String		       campaignReasonCode;
    private String		       campaignSubReasonCode;
    private String		       channel;
    private String		       ctiId;
    private String		       callId;
    private Integer		       interactionId;
    private String		       offerId;
    private String		       subscriberId;
    private String		       user;
    private Date		       feedbackDate;
    private ServiceIdentifierType      serviceIdentifier;
    private ContactMediumType	       contactMediumType;
    private CustomerIdentificationType customeridentification;
}

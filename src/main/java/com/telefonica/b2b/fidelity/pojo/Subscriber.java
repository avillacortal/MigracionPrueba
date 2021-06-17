package com.telefonica.b2b.fidelity.pojo;

import com.telefonica.b2b.fidelity.entity.RtdmOffers;

import lombok.Data;

@Data
public class Subscriber {

    private String subscriberid;
    private String msisdn;
    private String category;
    private String subscriberState;

    private Principalcomponent principalcomponent;
    private RtdmOffers	       parameters;

}

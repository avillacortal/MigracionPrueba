package com.telefonica.b2b.fidelity.entity.strategy;

import com.telefonica.b2b.fidelity.type.OfferType;

public class Catalog {

    private ICatalogStrategy strategy;

    public Catalog(ICatalogStrategy strategy) {
	this.strategy = strategy;
    }

    public void buildOfferType(OfferType offerType) {
	strategy.buildOfferType(offerType);
    }
}

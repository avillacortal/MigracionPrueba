package com.telefonica.b2b.fidelity.enums;

import java.util.Arrays;

public enum SubscriberStateEnum {

    RESERVED("R"),

    COLLECTION_WAS_SUSPENSION("D"),

    PRE_ACTIVATED("P"),

    COLLECTION_WAS_ACTIVE("U"),

    TERMINATED("T"),

    ACTIVE("A"),

    SUSPENDED("S"),

    CANCELLED("C"),

    COLLECION_CANCELLATION("L");

    private String value;

    SubscriberStateEnum(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return String.valueOf(value);
    }

    public static SubscriberStateEnum fromValue(String text) {
	return Arrays.asList(values()).stream().filter(val -> val.value.equalsIgnoreCase(text)).findAny().orElse(null);
    }
    
}

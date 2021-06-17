package com.telefonica.b2b.fidelity.enums;

import java.util.Arrays;

public enum CategoryEnum {

    CONTROL("Control"),

    CARIBU("Caribu"),

    POSTPAID("Postpago"),

    PREPAID("Prepago");

    String value;

    CategoryEnum(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return String.valueOf(value);
    }

    public static CategoryEnum fromValue(String text) {
	return Arrays.asList(values()).stream().filter(val -> val.value.equalsIgnoreCase(text)).findAny().orElse(null);
    }

}

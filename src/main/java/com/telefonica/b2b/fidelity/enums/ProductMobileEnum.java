package com.telefonica.b2b.fidelity.enums;

import java.util.Arrays;

public enum ProductMobileEnum {

    MOVIL("Movil", "WirelessMainComponent"),

    BAM("BAM", "BAMMainComponent"),

    M2M("M2M", "M2MMainComponent"),

    DEFAULT("", "");

    private String value;
    private String valueBD;

    ProductMobileEnum(String value, String valueBD) {
	this.value = value;
	this.valueBD = valueBD;
    }

    @Override
    public String toString() {
	return String.valueOf(value);
    }

    public String toStringValueBD() {
	return String.valueOf(valueBD);
    }

    public static ProductMobileEnum fromValue(String text) {
	return Arrays.asList(values()).stream().filter(val -> val.value.equalsIgnoreCase(text)).findAny().orElse(DEFAULT);
    }

    public static ProductMobileEnum fromValueBD(String text) {
	return Arrays.asList(values()).stream().filter(val -> val.valueBD.equalsIgnoreCase(text)).findAny().orElse(DEFAULT);
    }
}

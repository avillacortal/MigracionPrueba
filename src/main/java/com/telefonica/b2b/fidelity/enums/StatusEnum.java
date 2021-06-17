package com.telefonica.b2b.fidelity.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The status to which the product is set
 */
public enum StatusEnum {
    NEW("new", ""),

    CREATED("created", ""),

    ACTIVE("active", "Activo"),

    PENDING("pending", ""),

    ABORTED("aborted", ""),

    SUSPENDED("suspended", "Suspendido"),

    CANCELLED("cancelled", ""),

    CANCELLING("cancelling", ""),

    TERMINATED("terminated", "En baja"),

    TERMINATING("terminating", ""),

    INFORMATION("information", ""),

    TRIAL("trial", ""),

    KEEP("keep", "");

    private String value;
    private String anotherValue;

    StatusEnum(String value, String anotherValue) {
	this.value = value;
	this.anotherValue = anotherValue;
    }

    @Override
    @JsonValue
    public String toString() {
	return String.valueOf(value);
    }

    public String toStringAnotherValue() {
	return String.valueOf(anotherValue);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
	return Arrays.asList(values()).stream().filter(val -> val.value.equalsIgnoreCase(text)).findAny().orElse(null);
    }

    public static StatusEnum fromAnotherValue(String value) {
	return Arrays.asList(values()).stream().filter(val -> val.anotherValue.equalsIgnoreCase(value)).findAny().orElse(null);
    }
}
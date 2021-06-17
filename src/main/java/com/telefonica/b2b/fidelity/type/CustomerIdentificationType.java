package com.telefonica.b2b.fidelity.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CustomerIdentificationType {
    private String nationalIdType;
    private String nationalId;
}

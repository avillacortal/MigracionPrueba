package com.telefonica.b2b.fidelity.type;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Validated
@Data
public class ExceptionType {
    @JsonProperty("exceptionId")
    private String exceptionId = null;

    @JsonProperty("exceptionText")
    private String exceptionText = null;

    @JsonProperty("moreInfo")
    private String moreInfo = null;

    @JsonProperty("userMessage")
    private String userMessage = null;

    public ExceptionType exceptionId(String exceptionId) {
	this.exceptionId = exceptionId;
	return this;
    }
}

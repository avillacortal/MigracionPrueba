package com.telefonica.b2b.fidelity.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.telefonica.b2b.fidelity.commons.Constant;
import com.telefonica.b2b.fidelity.service.IB2bFidelityService;
import com.telefonica.b2b.fidelity.type.DATOSRestQueryCampaignsType;
import com.telefonica.b2b.fidelity.type.ExceptionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "b2b-fidelity", description = "the b2b-fidelity API")
@RestController
public class B2bFidelity {

    @Autowired
    private IB2bFidelityService b2bService;

    @ApiOperation(value = "Retrieve a list of campaigns", nickname = "retrieveCampaigns", notes = "b2bFidelity", response = DATOSRestQueryCampaignsType.class, responseContainer = "List", tags = {
	    "b2bFidelity", })
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Campaigns retrieved successfully", response = DATOSRestQueryCampaignsType.class, responseContainer = "List"),
	    @ApiResponse(code = 400, message = "BAD REQUEST", response = ExceptionType.class),
	    @ApiResponse(code = 404, message = "NOT FOUND", response = ExceptionType.class),
	    @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = ExceptionType.class) })
    @GetMapping(value = "/retrieveCampaigns", produces = { "application/json" })

    public ResponseEntity<DATOSRestQueryCampaignsType> retrieveCampaigns(
	    @NotNull @ApiParam(value = "Es el tipo de documento", required = true) @Valid @RequestParam(value = "nationalIdType", required = true) String nationalIdType,
	    @NotNull @ApiParam(value = "Es el numero de documento", required = true) @Valid @RequestParam(value = "nationalId", required = true) String nationalId,
	    @NotNull @ApiParam(value = "Es el Tipo de Negocio Mobil o Fija", required = true) @Valid @RequestParam(value = "lineOfBusinessType", required = true) String lineOfBusinessType,
	    @ApiParam(value = "Es el canal de venta") @Valid @RequestParam(value = "salesChannel", required = false) String salesChannel) {
	HttpHeaders httpHeaders = new HttpHeaders();
	DATOSRestQueryCampaignsType response = b2bService.getCampaigns(nationalIdType, nationalId, salesChannel, lineOfBusinessType);
	if (response.getResultCode() == null) {
	    response.setResultCode(Constant.CODE_OK_REQ);
	    response.setDesc(Constant.DESC_OK_REQ);
	}
	return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }

}

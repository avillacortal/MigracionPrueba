package com.telefonica.b2b.fidelity.exception;

import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.telefonica.b2b.fidelity.commons.Constant;
import com.telefonica.b2b.fidelity.commons.MessageProp;
import com.telefonica.b2b.fidelity.commons.Util;
import com.telefonica.b2b.fidelity.type.ExceptionType;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Autowired
    private MessageProp prop;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	ExceptionType error = new ExceptionType();
	error.setExceptionId(Constant.GENERIC_CODE);
	error.setExceptionText(ex.getMessage());
	error.setMoreInfo(prop.obtainDetail(Constant.GENERIC_CODE));
	error.setUserMessage(Constant.USERMESSAGE_TEXT);
	LOGGER.error("TrackingId: ".concat(Util.getTracking()));
	LOGGER.error(ExceptionUtils.getStackTrace(ex));
	return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
	ExceptionType error = new ExceptionType();
	error.setExceptionId(ex.getMessage());
	error.setExceptionText(Constant.FUNCTIONAL_ERROR);
	error.setMoreInfo(prop.obtainDetail(ex.getMessage()));
	error.setUserMessage(Constant.USERMESSAGE_TEXT);
	LOGGER.error("TrackingId: ".concat(Util.getTracking()));
	this.logThrowingBusinessMethod(ex);
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    private void logThrowingBusinessMethod(Exception ex) {
	StringBuilder log = new StringBuilder();
	StackTraceElement[] trace = null;
	if (ex instanceof BusinessException) {
	    log.append("[Message]: ");
	    log.append(prop.obtainDetail(ex.getMessage()));
	    log.append(Constant.NEW_LINE);
	    trace = ex.getStackTrace();
	    log.append(Constant.NEW_LINE);
	    log.append("[Exception Message]: ");
	    log.append(ex.toString());
	    log.append(Constant.NEW_LINE);
	    log.append("[Exception Trace]: ");
	    for (int i = 0; i <= trace.length - 1; i++) {
		log.append(i + " -> " + trace[i].toString());
		log.append(Constant.NEW_LINE);
		if (i == 25) {
		    break;
		}
	    }
	}
	log.append(Constant.NEW_LINE);
	logError(log.toString(), null);
    }

    private static void logError(String log, Exception e) {
	StringBuilder finalLog = new StringBuilder();
	finalLog.append(getHeaderForLog());
	finalLog.append(log);
	finalLog.append(getFooterLog());
	if (e != null) {
	    LOGGER.error(finalLog.toString(), e);
	} else {
	    LOGGER.error(finalLog.toString());
	}
    }

    private static String getHeaderForLog() {
	StringBuilder headerLog = new StringBuilder();
	headerLog.append(Constant.NEW_LINE + Constant.SEPARATOR + Constant.NEW_LINE);
	headerLog.append("[TrackingID]: ");
	headerLog.append(Util.getTracking());
	headerLog.append(Constant.NEW_LINE);
	headerLog.append("[Date]: ");
	headerLog.append(new Date());
	headerLog.append(Constant.NEW_LINE);
	headerLog.append("[Time]: ");
	headerLog.append(Util.getDateFormat(new Date(), Constant.TIME));
	headerLog.append(Constant.NEW_LINE + "" + Constant.NEW_LINE);
	return headerLog.toString();
    }

    private static String getFooterLog() {
	return Constant.SEPARATOR;
    }

}

package com.telefonica.b2b.fidelity.commons;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.telefonica.b2b.fidelity.enums.StatusEnum;
import com.telefonica.b2b.fidelity.enums.SubscriberStateEnum;
import com.telefonica.b2b.fidelity.type.AdditionalInformationType;

public class Util {

    public static String getTracking() {
	return Thread.currentThread().getName();
    }

    /**
     * Método de formato de fecha y hora utilizado en la clase LoggingAspect.
     * 
     * @return date
     */
    public static String getDateTimeFormatter() {
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constant.DATE_TIME);
	LocalDateTime localDateTime = LocalDateTime.now();
	return dateTimeFormatter.format(localDateTime);
    }

    public static String getDateFormat(Date date, String exp) {
	SimpleDateFormat sdf = new SimpleDateFormat(exp);
	return sdf.format(date);
    }

    public static String getDate(Date datetime) {
	if (datetime != null) {
	    ZonedDateTime date = datetime.toInstant().atZone(ZoneId.of(Constant.ZONE_ID));
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE);
	    return date.toLocalDateTime().format(formatter);
	}
	return null;
    }

    public static Integer getMinConditionFixed(String condition) {
	int init = condition.indexOf(Constant.CAMPAIGN_CONDICION_MIN) + Constant.CAMPAIGN_CONDICION_MIN.length();

	return Integer.parseInt(condition.substring(init, condition.indexOf(Constant.CAMPAIGN_CONDICION_END, init)).trim());
    }

    public static Integer getMaxConditionFixed(String condition) {
	int init = condition.indexOf(Constant.CAMPAIGN_CONDICION_MAX) + Constant.CAMPAIGN_CONDICION_MAX.length();

	return Integer.parseInt(condition.substring(init, condition.indexOf(Constant.CAMPAIGN_CONDICION_END, init)).trim());
    }

    public static Date getDateTimeToMidnight(Date date) {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
	calendar.set(Calendar.HOUR_OF_DAY, 0);
	calendar.set(Calendar.MINUTE, 0);
	calendar.set(Calendar.SECOND, 0);
	calendar.set(Calendar.MILLISECOND, 0);
	return calendar.getTime();
    }

    public static String generateResponseTracking() {
	UUID uuid = UUID.randomUUID();
	String pattern = Constant.PATTERN_FORMAT;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());
	return Constant.CAMPAIGN_CAMP.concat(date.concat(
		uuid.toString().replace(Constant.DASH, Constant.EMPTY).substring(Constant.ZERO_INT, Constant.THREE_INT).toUpperCase()));
    }

    public static Set<String> subscriberStateActive() {
	Set<String> statusActive = new HashSet<>();
	statusActive.add(SubscriberStateEnum.RESERVED.toString());
	statusActive.add(SubscriberStateEnum.COLLECTION_WAS_SUSPENSION.toString());
	statusActive.add(SubscriberStateEnum.PRE_ACTIVATED.toString());
	statusActive.add(SubscriberStateEnum.COLLECTION_WAS_ACTIVE.toString());
	statusActive.add(SubscriberStateEnum.ACTIVE.toString());
	statusActive.add(SubscriberStateEnum.SUSPENDED.toString());
	return statusActive;
    }

    public static Set<String> statusActive() {
	Set<String> statusActive = new HashSet<>();
	statusActive.add(StatusEnum.ACTIVE.toStringAnotherValue().toUpperCase());
	statusActive.add(StatusEnum.SUSPENDED.toStringAnotherValue().toUpperCase());
	return statusActive;
    }

    public static AdditionalInformationType buildAdditionalInformation(String dataName, String dataValue) {
	AdditionalInformationType additionalInformation = null;
	if (StringUtils.isNotBlank(dataName) || StringUtils.isNotBlank(dataValue)) {
	    additionalInformation = new AdditionalInformationType();
	    additionalInformation.setDataName(dataName);
	    additionalInformation.setDataValue(dataValue);
	}
	return additionalInformation;
    }

    public static int parseStringToInt(String value) {
	return value == null ? 0 : Integer.parseInt(value);
    }

}

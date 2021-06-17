package com.telefonica.b2b.fidelity.commons;

public class Constant {

    public static final String CODE_0002 = "0002";

    public static final String VALUE_WRLS  = "WRLS";
    public static final String VALUE_VOICE = "VOICE";
    public static final String VALUE_TV	   = "TV";

    public static final String NEW_LINE		= System.getProperty("line.separator");
    public static final String CLASS_LOG_LABEL	= "[Class]: ";
    public static final String METHOD_LOG_LABEL	= "[Method]: ";

    public static final String PARAMETERS_LOG_LABEL   = "(...)";
    public static final String INPUT_PARAMETERS_LABEL = "[Input Params]: ";
    public static final String OUTPUT_LABEL	      = "[Output]: ";
    public static final String SEPARATOR	      = "===================================================================================================================================================================================";
    public static final String EXCEPTION_WAS_THROWN   = "-> An exception was thrown by method: ";
    public static final String PARSE_JSON_RESPONSE    = "[No se pudo analizar el JSON del response del método (Verificar el Log)]";
    public static final String DEV_ENVIRONMENT	      = "dev";

    /**
     * Formato de Fecha
     */
    public static final String DATE_TIME      = "dd/MM/yy ' ' HH:mm:ss";
    public static final String DATE	      = "dd/MM/yyyy";
    public static final String TIME	      = "HH:mm:ss";
    public static final String ZONE_ID	      = "UTC";
    public static final String PATTERN_FORMAT = "MMddHHmmss";

    /**
     * Codigos de error
     */
    public static final String GENERIC_CODE	= "-1000";
    public static final String BE__1001		= "-1001";
    public static final String BE_1021		= "1021";
    public static final String BE_1205		= "1205";
    public static final String USERMESSAGE_TEXT	= "Generic Server Error.";
    public static final String FUNCTIONAL_ERROR	= "FunctionalError";
    public static final String BE_1380		= "1380";
    public static final String BE_1381		= "1381";
    public static final String BE_1382		= "1382";
    public static final String BE_1383		= "1383";
    public static final String BE_1384		= "1384";
    public static final String BE_1385		= "1385";
    public static final String BE_1386		= "1386";

    /**
     * Filtros
     */
    public static final String EMPTY			   = "";
    public static final String CAMPAIGN_TYPE		   = "BLINDAJE";
    public static final String CAMPAIGN_CAMP		   = "C";
    public static final String CAMPAIGN_CONDICION_MIN	   = ">=";
    public static final String CAMPAIGN_CONDICION_MAX	   = "<=";
    public static final String CAMPAIGN_CONDICION_END	   = "M";
    public static final String CAMPAIGN_MOBILE_RTDM_OFFERS = "Movil";
    public static final String CAMPAIGN_FIXED_RTDM_OFFERS  = "Fija";
    public static final String RESIDENTIAL		   = "RESIDENCIAL";
    public static final String RESIDENTIAL2		   = "RE";
    public static final int    ZERO_INT			   = 0;
    public static final int    THREE_INT		   = 3;
    public static final String DASH			   = "-";
    public static final int    SPEED_VALUE		   = 200;
    public static final String RTDMOFFERS_CAMPAIGNTYPE	   = "BLINDAJE";
    public static final String COD_RESUL_LLAM		   = "CEF";

    /**
     * Response
     */
    public static final String CAMPAIGNS_CAMPAIGNID   = "CAMP_BLINDAJE";
    public static final String CAMPAIGNS_CAMPAIGNTYPE = "BLINDAJE";
    public static final String CAMPAIGNS_CAMPAIGNNAME = "CAMP. FIDELIZACIÓN";
    public static final String CAMPAIGNS_PRIORITY     = "1";

    public static final String CAMPAIGN_ADDITIONAL_DATESTARTKEY	= "FECHAINICIOCAMPANA";
    public static final String CAMPAIGN_ADDITIONAL_DATEENDKEY	= "FECHAFINCAMPANA";
    public static final String CAMPAIGN_ADDITIONAL_PHONENUMBER	= "PHONENUMBER";

    public static final String OFFERS_CHANNEL	 = "SS";
    public static final String OFFERS_OFFERPRICE = "0";

    /**
     * Offer Additional Information
     */
    public static final String OFFER_ADDITIONAL_USSDDESCRIPTION	    = "DESCRIPCIONCOMERCIALUSSD";
    public static final String OFFER_ADDITIONAL_EFFECTIVEDATE	    = "EFFECTIVEDATE";
    public static final String OFFER_ADDITIONAL_EXPIRATIONDATE	    = "EXPIRATIONDATE";
    public static final String OFFER_ADDITIONAL_SALESEFFECTIVEDATE  = "SALEEFFECTIVEDATE";
    public static final String OFFER_ADDITIONAL_SALESEXPIRATIONDATE = "SALEEXPIRATIONDATE";
    public static final String OFFER_ADDITIONAL_SALESTYPE	    = "TIPOVENTA";
    public static final String OFFER_ADDITIONAL_PACKAGEDUO	    = "INDICADORPAQUETEDUO";

    public static final String OFFER_ADDITIONAL_KEYWORD		  = "KEYWORD";
    public static final String OFFER_ADDITIONAL_RECURRENCEPERIOD  = "PERIODORECURRENCIA";
    public static final String OFFER_ADDITIONAL_PACKAGETYPE	  = "TIPOPAQUETE";
    public static final String OFFER_ADDITIONAL_DELIVERYTYPE	  = "TIPOENTREGA";
    public static final String OFFER_ADDITIONAL_MCSSDESCRIPTION	  = "DESCRIPCIONCOMERCIALMULTICHANNEL";
    public static final String OFFER_ADDITIONAL_AMOUNTPACKAGETYPE = "AMOUNT_PACKAGE_TYPE";
    public static final String OFFER_ADDITIONAL_PERIOD		  = "PERIOD";

    public static final String CODE_OK_REQ = "0000";
    public static final String DESC_OK_REQ = "Transacción realizada con Exito";

    private Constant() {
    }
}

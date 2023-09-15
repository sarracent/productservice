package com.cloudx.productservice.utility;

import com.cloudx.productservice.constants.Errors;
import com.cloudx.productservice.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The type Util.
 */
public class Util {
    private static final ObjectMapper mapperJson = getMapperJson();

    private Util() {
    }

    /**
     * Generate mapper without WRITE_DATES_AS_TIMESTAMPS.
     *
     * @return the static ObjectMapper
     */
    public static ObjectMapper getMapperJson() {
        ObjectMapper mapperJson = new ObjectMapper();
        mapperJson.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapperJson.registerModule(new JavaTimeModule());
        mapperJson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapperJson;
    }

    /**
     * Generate unique id string.
     *
     * @param pKeyConcactToId the p key concact to id
     * @return the string
     */
    public static String generateUniqueId(String pKeyConcactToId) {
        if (isNullOrEmpty(pKeyConcactToId))
            return UUID.randomUUID().toString().replace("-", "");
        return UUID.randomUUID().toString().replace("-", "") + "-" + pKeyConcactToId;
    }

    /**
     * Is null or empty.
     *
     * @param pText the p text
     * @return the boolean
     */
    public static boolean isNullOrEmpty(String pText) {
        return pText == null || pText.isEmpty();
    }

    /**
     * Is null or empty.
     *
     * @param list the List
     * @return the boolean
     */
    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * Index exists boolean.
     *
     * @param list  the list
     * @param index the index
     * @return the boolean
     */
    public static boolean indexExists(final List<?> list, final int index) {
        return index >= 0 && index < list.size();
    }

    /**
     * Converts an Object to JSONString.
     *
     * @param input the input
     * @return the string
     */
    public static String toJSONString(Object input) {
        if (input == null) return null;
        try {
            return mapperJson.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            return String.format(Errors.ERROR_GENERAL.getMessage(), e.getMessage(), e);
        }
    }

    /**
     * Converts an Object to Map.
     *
     * @param input the Object
     * @return the Map
     */
    public static Map toObjectMap(Object input) {
        if (input == null) return new HashMap<>();
        try {
            return mapperJson.convertValue(input, Map.class);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    /**
     * Validate if the string value is supported the list of strings
     *
     * @param value          the string to validate in the list
     * @param valueSupported the string converted to list separated for regex
     * @param regex          the string separated
     * @return the boolean
     */
    public static boolean validateValueIsSupported(Object value, String valueSupported, String regex) {
        List<String> valueSupportedList = Arrays.asList(valueSupported.split(regex));
        return valueSupportedList.contains(String.valueOf(value));
    }

    /**
     * LocalDate converted to string by config pattern
     *
     * @param date    the LocalDate
     * @param pattern the string pattern
     * @return the string
     */
    public static String getFormatLocalDate(LocalDate date, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(date);
    }

    /**
     * String converted to List separated by field
     *
     * @param value     the string value to convert in list
     * @param separator the string separator
     * @return the list
     */
    public static List<String> getValueList(String value, String separator) {
        if (separator == null) return List.of(value);
        if (separator.contains(Constants.HASH))
            separator = Constants.HASH;
        else if (separator.contains(Constants.SEMICOLON))
            separator = Constants.SEMICOLON;
        else if (separator.contains(Constants.COMMA))
            separator = Constants.COMMA;
        else if (separator.contains(Constants.FORWARD_SLASH))
            separator = Constants.FORWARD_SLASH;
        else
            separator = "N/A";

        String stringToList = value.startsWith(separator) ? value.replaceFirst(separator, "") : value;
        return Arrays.asList(stringToList.split(separator));
    }

    /**
     * Convert message with throwable line code error to String.
     *
     * @param message   the message error
     * @param throwable the throwable to get line code error
     * @return the string
     */
    public static String getMsgWithLineCodeError(String message, Throwable throwable) {
        try {
            String lineCodeError = Arrays.stream(throwable.getStackTrace()).findFirst().map(StackTraceElement::toString).orElse(null);
            if (message == null) return lineCodeError;
            return String.format(Constants.PARSE_EXCEPTION, message, lineCodeError);
        } catch (Exception e) {
            return null;
        }
    }

}

package com.cloudx.productservice.commons.resolver;


import com.cloudx.productservice.exception.impl.ControllersException;
import com.cloudx.productservice.constants.Errors;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cloudx.productservice.constants.Constants.*;

public class CustomHeadersResolver {

    private CustomHeadersResolver() {
    }

    public static Map<String, String> generateHeadersMap(Map<String, Object> parametersMap) {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put(SERVICE_NAME, searchValueHeader(parametersMap, SERVICE_NAME.toLowerCase()));

        return headersMap;
    }

    public static Map<String, String> getHeadersMap(Map<String, Object> parametersMap) {
        return generateHeadersMap(parametersMap);
    }

    public static HttpHeaders getHttpHeaders(Map<String, String> parametersMap) {
        HttpHeaders httpHeaders = new HttpHeaders();
        parametersMap.forEach(httpHeaders::add);
        return httpHeaders;
    }

    private static String searchValueHeader(Map<String, Object> parametersMap, String header) {
        return parametersMap.get(header) == null ? "" : parametersMap.get(header).toString();
    }

    public static void validateHeaders(Map<String, String> headersMap) {
        List<String> messageList = new ArrayList<>();
        if (headersMap.get(SERVICE_NAME).isEmpty())
            messageList.add(SERVICE_NAME);

        if (!messageList.isEmpty())
            throw new ControllersException(Errors.ERROR_BUSINESS_VALIDATION_NULL_OR_EMPTY.getCode(),
                    String.format(Errors.ERROR_BUSINESS_VALIDATION_NULL_OR_EMPTY.getMessage(),
                            String.join(", ", messageList)));
    }
}

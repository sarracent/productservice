package com.cloudx.productservice.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Logs {

    @Getter
    @AllArgsConstructor
    public enum Header {
        TRANSACTION_ID("Transaction-Id", "Tansaction-Id generated by service."),
        SERVICE_ID("Service-Id", "Service-Id sent by client. It is the field that is used to indicate the name of the service that the application consumes.");

        private final String id;
        private final String description;
    }

    @Getter
    @AllArgsConstructor
    public enum Basic {
        OPERATION,
        CODE,
        DESCRIPTION,
        ELAPSED,
        REQUEST,
        RESPONSE,
    }

    @Getter
    @AllArgsConstructor
    public enum Extras {
        START_TIME,
        ERROR_EXECUTE,
        COUNTRY,
        HOST,
        APP,
        OPERATION_INTERMEDIATE,
        CODE_INTERMEDIATE,
        DESCRIPTION_INTERMEDIATE,
        ELAPSED_INTERMEDIATE,
        REQUEST_INTERMEDIATE,
        RESPONSE_INTERMEDIATE,
    }

    @Getter
    @AllArgsConstructor
    public enum Pattern {
        SEPARATOR(" | "),
        EXTRA_FORMAT("%s=%s%s"),
        GENERIC_DATA(" -> %s [%s]");

        private String description;
    }
}

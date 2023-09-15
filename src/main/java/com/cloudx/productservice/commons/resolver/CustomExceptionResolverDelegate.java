package com.cloudx.productservice.commons.resolver;

import com.cloudx.productservice.constants.Errors;
import com.cloudx.productservice.exception.CustomException;
import com.cloudx.productservice.model.response.ServiceResponse;

public class CustomExceptionResolverDelegate {

    private CustomExceptionResolverDelegate() {
    }

    public static ServiceResponse buildServiceResponse(Exception ex) {
        if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            return ServiceResponse.builder()
                    .resultCode(customException.getCode())
                    .resultMessage(customException.getMessage())
                    .build();
        } else {
            return ServiceResponse.builder()
                    .resultCode(Errors.ERROR_GENERAL.getCode())
                    .resultMessage(String.format(Errors.ERROR_GENERAL.getMessage(), ex.getMessage(), ex))
                    .build();
        }
    }

}
package com.cloudx.productservice.exception.impl;

import com.cloudx.productservice.exception.CustomException;
import com.cloudx.productservice.exception.ExceptionType;

import java.util.Collections;
import java.util.List;

public class InternalException extends RuntimeException implements CustomException {

    private static final long serialVersionUID = -1132348466005485433L;
    private final String code;
    private final String message;
    private final transient List<Object> extraInfo;

    public InternalException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        this.extraInfo = Collections.emptyList();
    }

    public InternalException(String code, String message, List<Object> extraInfo) {
        super(message);
        this.code = code;
        this.message = message;
        this.extraInfo = extraInfo;
    }

    @Override
    public ExceptionType getExceptionType() {
        return ExceptionType.INTERNAL_EXCEPTION;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<Object> getExtraInfo() {
        return extraInfo;
    }

}
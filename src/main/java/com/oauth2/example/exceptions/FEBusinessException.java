package com.oauth2.example.exceptions;


import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class FEBusinessException extends RuntimeException {

    private String errorCode;

    public FEBusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = checkErrorCode(errorCode);
    }

    private String checkErrorCode(String errorCode){
        return StringEscapeUtils.escapeHtml4(StringUtils.defaultString(errorCode).trim());
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

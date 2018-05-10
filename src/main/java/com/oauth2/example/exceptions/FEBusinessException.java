package com.oauth2.example.exceptions;


import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class FEBusinessException extends RuntimeException {

    private String errorCode;
    private List<String> errors;

    public FEBusinessException(String errorCode,List<String> errors) {
        super();
        this.errorCode = checkErrorCode(errorCode);
        this.errors = errors;
    }

    public FEBusinessException(String errorCode,String error) {
        super();
        this.errorCode = checkErrorCode(errorCode);
        this.errors = Arrays.asList(error);
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

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}

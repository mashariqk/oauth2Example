package com.oauth2.example.exceptions;


import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class FEBusinessException extends RuntimeException {

    private String errorCode;
    private List<String> errors;
    private HttpStatus status;

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

    public FEBusinessException(String errorCode, List<String> errors, HttpStatus status) {
        this.errorCode = errorCode;
        this.errors = errors;
        this.status = status;
    }

    public FEBusinessException(String errorCode, String error, HttpStatus status) {
        this.errorCode = errorCode;
        this.errors = Arrays.asList(error);
        this.status = status;
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

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}

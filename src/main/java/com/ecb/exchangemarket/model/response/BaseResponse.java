package com.ecb.exchangemarket.model.response;

import org.springframework.http.HttpStatus;

public class BaseResponse {
    private String responseCode;
    private String responseMessage;

    public BaseResponse() {
        this.responseCode = "" + HttpStatus.OK.value();
        this.responseMessage = "Request successfully processed";
    }

    public BaseResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}

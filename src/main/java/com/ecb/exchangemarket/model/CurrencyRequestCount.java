package com.ecb.exchangemarket.model;

import java.util.Map;

public class CurrencyRequestCount extends Currency {
    private Integer requestCount;

    public CurrencyRequestCount() {
    }

    public CurrencyRequestCount(Map.Entry<String, Integer> currency) {
        super(currency.getKey());
        requestCount = currency.getValue();
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }
}

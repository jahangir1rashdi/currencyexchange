package com.ecb.exchangemarket.model;

import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class CurrencyRate extends Currency {
    private Double rate;
    private String graphUrl;

    public CurrencyRate() {

    }

    public CurrencyRate(String currency, Double rate, String graphUrl) {
        super(currency) ;
        this.rate = rate;
        this.graphUrl = graphUrl;
    }


    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getGraphUrl() {
        return graphUrl;
    }

    public void setGraphUrl(String graphUrl) {
        this.graphUrl = graphUrl;
    }
}

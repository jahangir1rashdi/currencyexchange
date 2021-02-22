package com.ecb.exchangemarket.model;

public class CurrencyExchangeValue extends Currency {
    private Double value;

    public CurrencyExchangeValue() {
    }

    public CurrencyExchangeValue(String currency, Double value) {
        super(currency);
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}

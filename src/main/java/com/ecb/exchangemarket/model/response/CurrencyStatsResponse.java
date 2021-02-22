package com.ecb.exchangemarket.model.response;

import com.ecb.exchangemarket.model.CurrencyRequestCount;

import java.util.List;

public class CurrencyStatsResponse extends BaseResponse{
    private List<CurrencyRequestCount> currencies;

    public CurrencyStatsResponse(List<CurrencyRequestCount> currencies) {
        this.currencies = currencies;
    }

    public List<CurrencyRequestCount> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyRequestCount> currencies) {
        this.currencies = currencies;
    }
}

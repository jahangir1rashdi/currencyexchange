package com.ecb.exchangemarket.model.response;

import com.ecb.exchangemarket.model.Currency;

public class BaseCurrencyResponse extends BaseResponse {

    public BaseCurrencyResponse(Currency currency) {
        this.currency = currency;
    }

    private Currency currency;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}

package com.ecb.exchangemarket.model;


import com.ecb.exchangemarket.exception.UnSupportedCurrencyException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CurrencyStats {

    private static Map<String, Integer> currencies = new HashMap<>();

    public static Map<String, Integer> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(Map<String, Integer> currencies) {
        CurrencyStats.currencies = currencies;
    }

    public static void addRequestCount(String targetCurrency) {
        Integer value = Optional.of(currencies.get(targetCurrency))
                .orElseThrow(() -> new UnSupportedCurrencyException(targetCurrency));
        currencies.put(targetCurrency, ++value);
    }
}

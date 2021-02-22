package com.ecb.exchangemarket.util;

import com.ecb.exchangemarket.exception.UnSupportedCurrencyException;
import com.ecb.exchangemarket.model.CurrencyDataSet;

import java.util.Arrays;
import java.util.Optional;

public class Validator {

    public static Boolean isValidCurrencies(String... currencies) {
        Optional<String> unSupportedCurrencyOptional = Arrays.stream(currencies)
                .filter(currency ->  Boolean.FALSE.equals(CurrencyDataSet.getCurrencies().containsKey(currency)) )
                .findFirst();
        unSupportedCurrencyOptional.ifPresent(currency -> {
            throw new UnSupportedCurrencyException(currency);
        });
        return true;
    }
}

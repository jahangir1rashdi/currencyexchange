package com.ecb.exchangemarket.controller;

import com.ecb.exchangemarket.model.CurrencyExchangeValue;
import com.ecb.exchangemarket.model.CurrencyRate;
import com.ecb.exchangemarket.model.CurrencyRequestCount;
import com.ecb.exchangemarket.model.response.BaseCurrencyResponse;
import com.ecb.exchangemarket.model.response.CurrencyStatsResponse;
import com.ecb.exchangemarket.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping(value = "/exchange-rate/{targetCurrency}/{baseCurrency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getExchangeRate(@PathVariable String targetCurrency,
                                             @PathVariable String baseCurrency) {
        CurrencyRate currencyRate = currencyService.getExchangeRate(targetCurrency, baseCurrency);
        return new ResponseEntity<>(new BaseCurrencyResponse(currencyRate), HttpStatus.OK);
    }

    @GetMapping(value = "/convert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> convertCurrency(@RequestParam String targetCurrency,
                                             @RequestParam String baseCurrency,
                                             @RequestParam Double value) {

        CurrencyExchangeValue currencyExchangeValue = currencyService.convertCurrency(targetCurrency, baseCurrency, value);
        return new ResponseEntity<>(new BaseCurrencyResponse(currencyExchangeValue), HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> currencyList() {
        List<CurrencyRequestCount> currencyRate = currencyService.getCurrencyList();
        return new ResponseEntity<>(new CurrencyStatsResponse(currencyRate), HttpStatus.OK);
    }
}

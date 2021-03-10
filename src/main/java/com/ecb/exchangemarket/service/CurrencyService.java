package com.ecb.exchangemarket.service;

import com.ecb.exchangemarket.model.*;
import com.ecb.exchangemarket.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ecb.exchangemarket.util.Constant.BASE_CURRENCY_LABEL;
import static com.ecb.exchangemarket.util.Constant.TARGET_CURRENCY_LABEL;

@Service
public class CurrencyService {

    @Value(value = "${currency.graph.url}")
    private String graphUrl;

    private CurrencyDS currencyDS;

    Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    @Autowired
    public void setCurrencyDS(CurrencyDS currencyDS) {
        this.currencyDS = currencyDS;
    }

    @PostConstruct
    public void init() {
        logger.info("service initialized");
        currencyDS.initializeDS();
        initCurrenciesStats();
    }

    public void initCurrenciesStats() {
        Map<String, Integer> currenciesStats = CurrencyDataSet.getCurrencies().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, value -> 0));
        CurrencyStats.setCurrencies(currenciesStats);
    }

    public CurrencyRate getExchangeRate(String targetCurrency, String baseCurrency) {
        currencyDS.updateDS();
        Validator.isValidCurrencies(targetCurrency, baseCurrency);
        CurrencyStats.addRequestCount(targetCurrency);
        String url = getGraphUrl(targetCurrency, baseCurrency);
        return new CurrencyRate(targetCurrency, getRate(targetCurrency, baseCurrency), url);
    }

    public CurrencyExchangeValue convertCurrency(String targetCurrency, String baseCurrency, Double amount) {
        currencyDS.updateDS();
        Validator.isValidCurrencies(targetCurrency, baseCurrency);
        Double exchangeValue = amount * getRate(targetCurrency, baseCurrency);
        return new CurrencyExchangeValue(targetCurrency, exchangeValue);
    }


    public List<CurrencyRequestCount> getCurrencyList() {
        currencyDS.updateDS();
        return CurrencyStats.getCurrencies().entrySet().stream()
                .map(CurrencyRequestCount::new)
                .collect(Collectors.toList());
    }

    private Double getRate(String targetCurrency, String baseCurrency) {
        if (CurrencyDataSet.getBaseCurrency().equals(baseCurrency)) {
            return CurrencyDataSet.getCurrencies().get(targetCurrency);
        }
        return CurrencyDataSet.getCurrencies().get(targetCurrency) / CurrencyDataSet.getCurrencies().get(baseCurrency);
    }

    private String getGraphUrl(String targetCurrency, String baseCurrency) {
        String url = new String(graphUrl).replace(BASE_CURRENCY_LABEL, baseCurrency);
        return url.replace(TARGET_CURRENCY_LABEL, targetCurrency);
    }


}

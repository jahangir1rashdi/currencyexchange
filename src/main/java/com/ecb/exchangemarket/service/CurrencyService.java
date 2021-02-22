package com.ecb.exchangemarket.service;

import com.ecb.exchangemarket.exception.ExchangeMarketException;
import com.ecb.exchangemarket.model.*;
import com.ecb.exchangemarket.util.Validator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.ecb.exchangemarket.util.Constant.BASE_CURRENCY_LABEL;
import static com.ecb.exchangemarket.util.Constant.TARGET_CURRENCY_LABEL;

@Service
public class CurrencyService {

    @Value(value = "${currency.graph.url}")
    private String graphUrl;

    @Autowired
    private CurrencyDS currencyDS;

    @PostConstruct
    public void init() {
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

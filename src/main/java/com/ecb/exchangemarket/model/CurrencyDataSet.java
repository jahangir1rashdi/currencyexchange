package com.ecb.exchangemarket.model;

import com.ecb.exchangemarket.exception.ExchangeMarketException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class CurrencyDataSet implements CurrencyDS {

    private static final int COLUMN_NAME_INDEX = 0;
    private static final int COLUMN_VALUE_INDEX = 1;

    @Value(value = "${ecb.currency.dataset.csv.url}")
    private String currencyDSUrl;

    private static LocalDate dataSetDate;
    private static final String baseCurrency = "EUR";
    private static Map<String, Double> currencies = new HashMap<>();

    public static LocalDate getDataSetDate() {
        return dataSetDate;
    }

    public static void setDataSetDate(LocalDate dataSetDate) {
        CurrencyDataSet.dataSetDate = dataSetDate;
    }

    public static String getBaseCurrency() {
        return baseCurrency;
    }

    public static Map<String, Double> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(Map<String, Double> currencies) {
        CurrencyDataSet.currencies = currencies;
    }

    @Override
    public void initializeDS() {
        try {
            URL url = new URL(currencyDSUrl);
            InputStream input = url.openStream();
            ZipInputStream zipIn = new ZipInputStream(input);
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null) {
                InputStreamReader isr = new InputStreamReader(zipIn);
                CSVParser csv = new CSVParser(isr, CSVFormat.DEFAULT);
                List<CSVRecord> records = csv.getRecords();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                LocalDate datasetDate = LocalDate.parse(records.get(1).get(0).toString(), formatter);
                CurrencyDataSet.setDataSetDate(datasetDate);
                for (int i = 1; i < records.get(0).size() - 1; i++) {
                    CurrencyDataSet.getCurrencies().put(records.get(COLUMN_NAME_INDEX).get(i).toString().trim(),
                            Double.valueOf(records.get(COLUMN_VALUE_INDEX).get(i)));
                }
                CurrencyDataSet.getCurrencies().put(CurrencyDataSet.getBaseCurrency(), 1.0);
            }
        } catch (Exception e) {
            throw new ExchangeMarketException("Incorrect url or CSV format");
        }
    }

    public void updateDS() {
        if (!LocalDate.now().equals(dataSetDate)) {
            initializeDS();
        }
    }
}

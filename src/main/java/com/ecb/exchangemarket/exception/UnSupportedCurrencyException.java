package com.ecb.exchangemarket.exception;

import java.util.function.Supplier;

public class UnSupportedCurrencyException extends RuntimeException  {


    public UnSupportedCurrencyException() {
        super();
    }

    public UnSupportedCurrencyException(String currency) {
        super(String.format("Currency: %s is not supported", currency));
    }

    public UnSupportedCurrencyException(Throwable cause) {
        super(cause);
    }

    public UnSupportedCurrencyException(String message, Throwable throwable) {
        super(message, throwable);
    }

}

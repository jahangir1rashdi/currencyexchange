package com.ecb.exchangemarket.exception;

import com.ecb.exchangemarket.model.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CurrencyExceptionController {

    @ExceptionHandler(value = UnSupportedCurrencyException.class)
    public ResponseEntity<?> exception(UnSupportedCurrencyException ex) {
        BaseResponse response = new BaseResponse("" + HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<BaseResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ExchangeMarketException.class)
    public ResponseEntity<?> exception(ExchangeMarketException ex) {
        BaseResponse response = new BaseResponse("" + HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<BaseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

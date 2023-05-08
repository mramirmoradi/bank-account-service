package com.mobilab.exchangeconversionrateservice.CurrencyPair.Exception;

import com.mobilab.exchangeconversionrateservice.Base.Exception.BaseRuntimeException;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Constants.CurrencyPairExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceNotAvailableException extends BaseRuntimeException {
    public ServiceNotAvailableException() {super(CurrencyPairExceptionMessage.SERVER_NOT_AVAILABLE);}
}

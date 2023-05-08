package com.mobilab.exchangeconversionrateservice.CurrencyPair.Service;

import com.mobilab.exchangeconversionrateservice.Base.Enum.Currency;
import com.mobilab.exchangeconversionrateservice.Base.Service.BaseService;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Entity.CurrencyPair;

public interface CurrencyPairService extends BaseService<CurrencyPair> {

    CurrencyPair findByBaseCurrencyAndQuoteCurrency(Currency from, Currency to);
}

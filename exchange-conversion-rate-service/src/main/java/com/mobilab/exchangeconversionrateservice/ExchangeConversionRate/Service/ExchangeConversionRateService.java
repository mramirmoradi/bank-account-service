package com.mobilab.exchangeconversionrateservice.ExchangeConversionRate.Service;

import com.mobilab.exchangeconversionrateservice.Base.Enum.Currency;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.DTO.CurrencyPairResponseDTO;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Entity.CurrencyPair;

public interface ExchangeConversionRateService {

    CurrencyPair exchangeRateCalculation(Currency from, Currency to);
}

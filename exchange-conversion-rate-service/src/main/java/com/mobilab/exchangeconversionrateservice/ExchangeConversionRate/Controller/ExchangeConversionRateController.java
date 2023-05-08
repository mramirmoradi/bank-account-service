package com.mobilab.exchangeconversionrateservice.ExchangeConversionRate.Controller;

import com.mobilab.exchangeconversionrateservice.Base.Enum.Currency;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Constants.CurrencyPairUrlMapping;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.DTO.CurrencyPairResponseDTO;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Entity.CurrencyPair;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Mapper.CurrencyPairMapper;
import com.mobilab.exchangeconversionrateservice.ExchangeConversionRate.Constants.ExchangeConversionRateUrlMapping;
import com.mobilab.exchangeconversionrateservice.ExchangeConversionRate.Service.ExchangeConversionRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ExchangeConversionRateUrlMapping.WEBSERVICE_BASE_URL)
@RequiredArgsConstructor
public class ExchangeConversionRateController {

    private final ExchangeConversionRateService service;
    private final CurrencyPairMapper currencyPairMapper;

    @GetMapping("/{from}-{to}")
    public CurrencyPairResponseDTO find(@PathVariable Currency from, @PathVariable Currency to) {
        CurrencyPair currencyPair = service.exchangeRateCalculation(from, to);
        return currencyPairMapper.currencyPairToCurrencyPairResponseDTO(currencyPair);
    }
}

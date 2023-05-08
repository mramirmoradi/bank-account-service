package com.mobilab.exchangeconversionrateservice.ExchangeConversionRate.Service;

import com.mobilab.exchangeconversionrateservice.Base.Enum.Currency;
import com.mobilab.exchangeconversionrateservice.Base.Exception.NotFoundEntityException;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Constants.ExchangerType;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Entity.CurrencyPair;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Exception.ServiceNotAvailableException;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Service.CurrencyPairService;
import com.mobilab.exchangeconversionrateservice.OpenExchangeClient.DTO.OpenExchangeResponseDTO;
import com.mobilab.exchangeconversionrateservice.OpenExchangeClient.OpenExchangeClient;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExchangeConversionRateServiceImp implements ExchangeConversionRateService{

    @Value("${micro-config.default-pair-exchange-rate}")
    private double DEFAULT_PAIR_EXCHANGE_RATE;
    @Value("${micro-config.exchanger-type}")
    private int EXCHANGER_TYPE;
    @Value("${micro-config.open-exchange-app-id}")
    private String OPEN_EXCHANGE_APP_ID;

    private final CurrencyPairService currencyPairService;
    private final OpenExchangeClient openExchangeClient;

    public ExchangeConversionRateServiceImp(CurrencyPairService currencyPairService, OpenExchangeClient openExchangeClient) {
        this.currencyPairService = currencyPairService;
        this.openExchangeClient = openExchangeClient;
    }

    @Override
    public CurrencyPair exchangeRateCalculation(Currency from, Currency to) {

        if (from.equals(to))
            return new CurrencyPair(from, to, DEFAULT_PAIR_EXCHANGE_RATE);

        else if (EXCHANGER_TYPE == ExchangerType.OFFLINE_EXCHANGER.getValue())
            return offlineExchanger(from, to);

        else if (EXCHANGER_TYPE == ExchangerType.OPEN_EXCHANGER.getValue())
            return openExchanger(from, to);

        else
            throw new ServiceNotAvailableException();
    }

    public CurrencyPair offlineExchanger (Currency from, Currency to) {
        return currencyPairService.findByBaseCurrencyAndQuoteCurrency(from, to);
    }

    public CurrencyPair openExchanger(Currency from, Currency to) {
        Double exchangeRate;
        OpenExchangeResponseDTO response;
        try {
            response = openExchangeClient.request(OPEN_EXCHANGE_APP_ID);
        } catch (Exception e) {
            // Log microservice on message broker
            //throw new ServiceNotAvailableException();

            // If open does not respond, we go on offline conversion rate
            return offlineExchanger(from, to);
        }
        exchangeRate = response.getRates().get(Currency.EUR.name());

        if (from.equals(Currency.USD))
            return new CurrencyPair(from, to, exchangeRate);
        else
            return new CurrencyPair(from, to, 1/exchangeRate);

    }
}

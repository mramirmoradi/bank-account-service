package com.mobilab.exchangeconversionrateservice.CurrencyPair.Service;

import com.mobilab.exchangeconversionrateservice.Base.Enum.Currency;
import com.mobilab.exchangeconversionrateservice.Base.Exception.NotFoundEntityException;
import com.mobilab.exchangeconversionrateservice.Base.Service.BaseServiceImp;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Entity.CurrencyPair;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Repository.CurrencyPairRepository;
import com.mobilab.exchangeconversionrateservice.OpenExchangeClient.DTO.OpenExchangeResponseDTO;
import com.mobilab.exchangeconversionrateservice.OpenExchangeClient.OpenExchangeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Constants.ExchangerType;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Exception.ServiceNotAvailableException;

import java.rmi.server.ServerNotActiveException;
import java.util.Optional;

@Service
public class CurrencyPairServiceImp extends BaseServiceImp<CurrencyPair> implements CurrencyPairService {

    private final CurrencyPairRepository repository;

    public CurrencyPairServiceImp(CurrencyPairRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public CurrencyPair findByBaseCurrencyAndQuoteCurrency(Currency from, Currency to) {
        Optional<CurrencyPair> optionalCurrencyPair = repository.findByBaseCurrencyAndQuoteCurrency(from, to);
        if (optionalCurrencyPair.isEmpty())
            throw new NotFoundEntityException();
        return optionalCurrencyPair.get();
    }
}

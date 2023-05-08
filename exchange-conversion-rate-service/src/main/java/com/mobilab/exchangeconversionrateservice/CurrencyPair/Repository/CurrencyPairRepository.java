package com.mobilab.exchangeconversionrateservice.CurrencyPair.Repository;

import com.mobilab.exchangeconversionrateservice.Base.Enum.Currency;
import com.mobilab.exchangeconversionrateservice.Base.Repository.BaseRepository;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Entity.CurrencyPair;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyPairRepository extends BaseRepository<CurrencyPair> {

    Optional<CurrencyPair> findByBaseCurrencyAndQuoteCurrency(Currency baseCurrency, Currency quoteCurrency);
}

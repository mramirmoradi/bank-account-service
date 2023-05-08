package com.mobilab.exchangeconversionrateservice.CurrencyPair.DTO;

import com.mobilab.exchangeconversionrateservice.Base.Enum.Currency;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Constants.CurrencyPairValidationMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CurrencyPairRequestDTO {

    @NotNull(message = CurrencyPairValidationMessage.CURRENCY_SHOULD_NOT_NULL)
    public Currency baseCurrency;

    @NotNull(message = CurrencyPairValidationMessage.CURRENCY_SHOULD_NOT_NULL)
    public Currency quoteCurrency;

    @NotNull(message = CurrencyPairValidationMessage.EXCHANGE_RATE_NOT_NULL)
    public Double exchangeRate;
}

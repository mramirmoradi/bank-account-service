package com.mobilab.exchangeconversionrateservice.ExchangeConversionRate.Constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ExchangeConversionRateUrlMapping {

    public static final String API_VERSION = "/v1";
    public static final String API_BASE = "/exchange-conversion-rate";

    public static final String WEBSERVICE_BASE_URL = API_VERSION + API_BASE;
}

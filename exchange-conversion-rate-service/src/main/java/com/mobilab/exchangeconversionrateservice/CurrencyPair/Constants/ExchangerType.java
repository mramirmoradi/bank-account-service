package com.mobilab.exchangeconversionrateservice.CurrencyPair.Constants;

public enum ExchangerType {

    OFFLINE_EXCHANGER("offline_exchanger", 0),
    OPEN_EXCHANGER("open_exchanger", 1);

    private final String key;
    private final Integer value;

    ExchangerType(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }
}

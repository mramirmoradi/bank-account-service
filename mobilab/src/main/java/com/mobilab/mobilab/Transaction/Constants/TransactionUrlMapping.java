package com.mobilab.mobilab.Transaction.Constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class TransactionUrlMapping {

    public static final String API_VERSION = "/v1";
    public static final String API_BASE = "/transactions";

    public static final String WEBSERVICE_BASE_URL = API_VERSION + API_BASE;
}

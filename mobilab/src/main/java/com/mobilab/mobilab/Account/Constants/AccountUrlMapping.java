package com.mobilab.mobilab.Account.Constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class AccountUrlMapping {

    public static final String API_VERSION = "/v1";
    public static final String API_BASE = "/accounts";

    public static final String WEBSERVICE_BASE_URL = API_VERSION + API_BASE;
}

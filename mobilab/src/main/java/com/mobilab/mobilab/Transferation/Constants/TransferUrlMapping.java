package com.mobilab.mobilab.Transferation.Constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TransferUrlMapping {

    public static final String API_VERSION = "/v1";
    public static final String API_BASE = "/transfer";

    public static final String WEBSERVICE_BASE_URL = API_VERSION + API_BASE;
}

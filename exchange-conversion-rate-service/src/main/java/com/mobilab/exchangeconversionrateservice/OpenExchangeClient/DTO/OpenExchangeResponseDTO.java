package com.mobilab.exchangeconversionrateservice.OpenExchangeClient.DTO;

import lombok.Data;

import java.util.Map;

@Data
public class OpenExchangeResponseDTO {

    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private Map<String, Double> rates;
}

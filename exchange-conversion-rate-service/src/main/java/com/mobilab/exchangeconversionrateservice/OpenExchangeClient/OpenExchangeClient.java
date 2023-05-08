package com.mobilab.exchangeconversionrateservice.OpenExchangeClient;

import com.mobilab.exchangeconversionrateservice.OpenExchangeClient.DTO.OpenExchangeResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "exchanger", url = "https://openexchangerates.org/api/latest.json")
public interface OpenExchangeClient {

    @RequestMapping(method = RequestMethod.GET, value = "?app_id={app_id}", produces = "application/json")
    OpenExchangeResponseDTO request(@PathVariable("app_id") String appId);
}

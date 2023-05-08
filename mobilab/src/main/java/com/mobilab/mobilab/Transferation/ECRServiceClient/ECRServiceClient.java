package com.mobilab.mobilab.Transferation.ECRServiceClient;

import com.mobilab.mobilab.Transferation.ECRServiceClient.DTO.ECRServiceResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Feign going to use the Eureka for finding the service
@FeignClient(name = "ECR-service")
@Retry(name = "ECR-service")
public interface ECRServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/exchange-conversion-rate/{from}-{to}", produces = "application/json")
    ECRServiceResponse request(@PathVariable("from") String from, @PathVariable String to);
}

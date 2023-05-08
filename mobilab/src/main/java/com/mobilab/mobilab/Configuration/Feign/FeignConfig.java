//package com.mobilab.mobilab.Configuration.Feign;
//
//import com.mobilab.mobilab.Transferation.ExchangeClient.ExchangeClient;
//import feign.Contract;
//import feign.Feign;
//import feign.Logger;
//import feign.gson.GsonDecoder;
//import feign.gson.GsonEncoder;
//import feign.okhttp.OkHttpClient;
//import feign.slf4j.Slf4jLogger;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FeignConfig {
//
//    @Value("${exchange.url}")
//    private String EXCHANGE_URL;
//
//    @Bean
//    public Contract feignContract() {
//        return new feign.Contract.Default();
//    }
//
//    @Bean
//    public ExchangeClient getExchangeFeignClient(Contract contract) {
//        return Feign.builder()
//                .contract(contract)
//                .client(new OkHttpClient())
//                .encoder(new GsonEncoder())
//                .decoder(new GsonDecoder())
//                .logger(new Slf4jLogger(ExchangeClient.class))
//                .logLevel(Logger.Level.FULL)
//                .target(ExchangeClient.class, EXCHANGE_URL);
//    }
//}

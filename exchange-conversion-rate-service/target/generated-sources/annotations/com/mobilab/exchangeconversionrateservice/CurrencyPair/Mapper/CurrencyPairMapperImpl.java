package com.mobilab.exchangeconversionrateservice.CurrencyPair.Mapper;

import com.mobilab.exchangeconversionrateservice.CurrencyPair.DTO.CurrencyPairRequestDTO;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.DTO.CurrencyPairResponseDTO;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Entity.CurrencyPair;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-06T20:23:17+0430",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class CurrencyPairMapperImpl implements CurrencyPairMapper {

    @Override
    public CurrencyPair currencyPairRequestDTOToCurrencyPair(CurrencyPairRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        CurrencyPair currencyPair = new CurrencyPair();

        currencyPair.setBaseCurrency( requestDTO.getBaseCurrency() );
        currencyPair.setQuoteCurrency( requestDTO.getQuoteCurrency() );
        if ( requestDTO.getExchangeRate() != null ) {
            currencyPair.setExchangeRate( requestDTO.getExchangeRate() );
        }

        return currencyPair;
    }

    @Override
    public CurrencyPairResponseDTO currencyPairToCurrencyPairResponseDTO(CurrencyPair currencyPair) {
        if ( currencyPair == null ) {
            return null;
        }

        CurrencyPairResponseDTO currencyPairResponseDTO = new CurrencyPairResponseDTO();

        currencyPairResponseDTO.setExchangeRate( currencyPair.getExchangeRate() );

        return currencyPairResponseDTO;
    }
}

package com.mobilab.exchangeconversionrateservice.CurrencyPair.Mapper;

import com.mobilab.exchangeconversionrateservice.CurrencyPair.DTO.CurrencyPairRequestDTO;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.DTO.CurrencyPairResponseDTO;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Entity.CurrencyPair;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyPairMapper {

    CurrencyPair currencyPairRequestDTOToCurrencyPair(CurrencyPairRequestDTO requestDTO);

    CurrencyPairResponseDTO currencyPairToCurrencyPairResponseDTO(CurrencyPair currencyPair);

}

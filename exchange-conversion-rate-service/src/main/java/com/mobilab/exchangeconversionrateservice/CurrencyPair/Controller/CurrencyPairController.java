package com.mobilab.exchangeconversionrateservice.CurrencyPair.Controller;

import com.mobilab.exchangeconversionrateservice.Base.Enum.Currency;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Constants.CurrencyPairUrlMapping;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.DTO.CurrencyPairRequestDTO;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.DTO.CurrencyPairResponseDTO;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Entity.CurrencyPair;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Mapper.CurrencyPairMapper;
import com.mobilab.exchangeconversionrateservice.CurrencyPair.Service.CurrencyPairService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(CurrencyPairUrlMapping.WEBSERVICE_BASE_URL)
@RequiredArgsConstructor
public class CurrencyPairController {

    private final CurrencyPairService service;
    private final CurrencyPairMapper mapper;

    @PostMapping
    public CurrencyPair create(@Valid @RequestBody CurrencyPairRequestDTO requestDTO) {
        return service.create(mapper.currencyPairRequestDTOToCurrencyPair(requestDTO));
    }

    @GetMapping
    public Page<CurrencyPair> findAll(Pageable pageable) {
        return service.find(pageable);
    }
}

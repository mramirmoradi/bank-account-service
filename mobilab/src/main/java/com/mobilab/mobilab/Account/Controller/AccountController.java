package com.mobilab.mobilab.Account.Controller;

import com.mobilab.mobilab.Account.Constants.AccountUrlMapping;
import com.mobilab.mobilab.Account.DTO.AccountChargeDTO;
import com.mobilab.mobilab.Account.DTO.AccountCreateDTO;
import com.mobilab.mobilab.Account.DTO.AccountResponseDTO;
import com.mobilab.mobilab.Account.DTO.AccountUpdateDTO;
import com.mobilab.mobilab.Account.Mapper.AccountMapstruct;
import com.mobilab.mobilab.Account.Service.AccountService;
import com.mobilab.mobilab.Account.Specification.AccountSpecification;
import com.mobilab.mobilab.Base.Enum.Currency;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(AccountUrlMapping.WEBSERVICE_BASE_URL)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;
    private final AccountMapstruct mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public AccountResponseDTO create(@Valid @RequestBody AccountCreateDTO createDTO) {
        return mapper.accountToAccountResponseDTO(service.register(createDTO));
    }

    @PutMapping("/{number}")
    @ResponseStatus(OK)
    public AccountResponseDTO update(@Valid @RequestBody AccountUpdateDTO updateDTO, @PathVariable String number) {
        return mapper.
                accountToAccountResponseDTO(
                        service.customUpdate(
                                number,
                                updateDTO));
    }

    @DeleteMapping("/{number}")
    @ResponseStatus(OK)
    public void deleteByNumber(@PathVariable String number) {
        service.deleteByNumber(number);
    }

    @PostMapping("/charge/{number}")
    public AccountResponseDTO charge(@PathVariable String number, @RequestBody AccountChargeDTO chargeDTO) {
        return mapper.accountToAccountResponseDTO(service.charge(number, chargeDTO));
    }

    @GetMapping
    @ResponseStatus(OK)
    public Page<AccountResponseDTO> get(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(name = "number", required = false) String number,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "customerId", required = false) Long customerId,
            @RequestParam(name = "currency", required = false) Currency currency,
            @RequestParam(name = "deleted", required = false) Boolean deleted,
            Pageable pageable) {
        AccountSpecification specification = new AccountSpecification(id, startDate, endDate, number, name, customerId, currency, deleted);
        return service.find(specification, pageable)
                .map(mapper::accountToAccountResponseDTO);
    }
}

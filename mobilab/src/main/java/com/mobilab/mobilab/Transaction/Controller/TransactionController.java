package com.mobilab.mobilab.Transaction.Controller;

import com.mobilab.mobilab.Transaction.Constants.TransactionUrlMapping;
import com.mobilab.mobilab.Transaction.DTO.TransactionResponseDTO;
import com.mobilab.mobilab.Transaction.Enum.TransactionStatus;
import com.mobilab.mobilab.Transaction.Enum.TransactionType;
import com.mobilab.mobilab.Transaction.Mapper.TransactionMapstruct;
import com.mobilab.mobilab.Transaction.Service.TransactionService;
import com.mobilab.mobilab.Transaction.Specification.TransactionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(TransactionUrlMapping.WEBSERVICE_BASE_URL)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;
    private final TransactionMapstruct mapper;

    @GetMapping("/search")
    public Page<TransactionResponseDTO> get(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(name = "accountNumber", required = false) String accountNumber,
            @RequestParam(name = "sourceAccountNumber", required = false) String sourceAccountNumber,
            @RequestParam(name = "destinationAccountNumber", required = false) String destinationAccountNumber,
            @RequestParam(name = "status", required = false) TransactionStatus status,
            @RequestParam(name = "trackingCode", required = false) String trackingCode,
            @RequestParam(name = "type", required = false) TransactionType type,
            Pageable pageable) {
        TransactionSpecification specification = new TransactionSpecification(id, startDate, endDate, accountNumber, sourceAccountNumber, destinationAccountNumber, status, trackingCode, type);
        return service.find(specification, pageable)
                .map(mapper::transactionToTransactionResponseDTO);
    }
}

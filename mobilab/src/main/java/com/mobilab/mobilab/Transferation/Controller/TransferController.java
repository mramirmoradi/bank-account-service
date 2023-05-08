package com.mobilab.mobilab.Transferation.Controller;

import com.mobilab.mobilab.Transferation.Constants.TransferUrlMapping;
import com.mobilab.mobilab.Transferation.DTO.TransferRequestDTO;
import com.mobilab.mobilab.Transferation.DTO.TransferResponseDTO;
import com.mobilab.mobilab.Transferation.Service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TransferUrlMapping.WEBSERVICE_BASE_URL)
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public TransferResponseDTO request(@Valid @RequestBody TransferRequestDTO requestDTO) {
        return transferService.transferRequest(requestDTO);
    }
}

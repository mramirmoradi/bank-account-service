package com.mobilab.mobilab.Transferation.Service;

import com.mobilab.mobilab.Transferation.DTO.TransferRequestDTO;
import com.mobilab.mobilab.Transferation.DTO.TransferResponseDTO;

public interface TransferService {

    TransferResponseDTO transferRequest(TransferRequestDTO requestDTO);
}

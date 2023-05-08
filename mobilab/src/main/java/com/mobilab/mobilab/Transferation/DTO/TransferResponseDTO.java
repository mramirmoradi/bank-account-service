package com.mobilab.mobilab.Transferation.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferResponseDTO {

    private String trackingCode;

    private String message;

    private Integer code;
}

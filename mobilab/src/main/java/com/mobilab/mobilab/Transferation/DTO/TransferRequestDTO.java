package com.mobilab.mobilab.Transferation.DTO;

import com.mobilab.mobilab.Transferation.Constants.TransferValidationMessages;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequestDTO {

    @NotNull(message = TransferValidationMessages.NOT_NULL_SOURCE_ACCOUNT_NUMBER)
    private String sourceAccountNumber;

    @NotNull(message = TransferValidationMessages.NOT_NULL_DESTINATION_ACCOUNT_NUMBER)
    private String destinationAccountNumber;

    @NotNull(message = TransferValidationMessages.NOT_NULL_AMOUNT)
    private Double amount;
}

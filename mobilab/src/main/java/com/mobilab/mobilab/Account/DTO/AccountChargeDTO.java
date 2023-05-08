package com.mobilab.mobilab.Account.DTO;

import com.mobilab.mobilab.Account.Constants.AccountValidationMessages;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountChargeDTO {

    @NotNull(message = AccountValidationMessages.NOT_NULL_AMOUNT)
    private double amount;

    @NotNull(message = AccountValidationMessages.NOT_NULL_CUSTOMER_ID)
    private Long customerId;
}

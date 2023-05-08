package com.mobilab.mobilab.Account.DTO;

import com.mobilab.mobilab.Account.Constants.AccountValidationMessages;
import com.mobilab.mobilab.Base.Enum.Currency;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountCreateDTO {

    @NotNull(message = AccountValidationMessages.NOT_NULL_NAME)
    public String name;

    public String description;

    @NotNull(message = AccountValidationMessages.NOT_NULL_CUSTOMER_ID)
    public Long customerId;

    @NotNull(message = AccountValidationMessages.NOT_NULL_CURRENCY)
    public Currency currency;

}

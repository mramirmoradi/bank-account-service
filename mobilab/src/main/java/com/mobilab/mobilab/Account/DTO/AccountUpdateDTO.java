package com.mobilab.mobilab.Account.DTO;

import com.mobilab.mobilab.Account.Constants.AccountValidationMessages;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountUpdateDTO {

    @NotNull(message = AccountValidationMessages.NOT_NULL_NAME)
    public String name;
    public String description;

}

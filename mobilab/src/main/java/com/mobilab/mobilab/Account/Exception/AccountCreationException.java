package com.mobilab.mobilab.Account.Exception;

import com.mobilab.mobilab.Account.Constants.AccountExceptionMessages;
import com.mobilab.mobilab.Base.Exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class AccountCreationException extends BaseRuntimeException {
    public AccountCreationException() {
        super(AccountExceptionMessages.ACCOUNT_CREATION_IS_NOT_POSSIBLE);
    }
}

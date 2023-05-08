package com.mobilab.mobilab.Account.Exception;

import com.mobilab.mobilab.Account.Constants.AccountExceptionMessages;
import com.mobilab.mobilab.Base.Exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class NotCustomerAccountException extends BaseRuntimeException {
    public NotCustomerAccountException() {
        super(AccountExceptionMessages.NOT_CUSTOMER_ACCOUNT);
    }
}

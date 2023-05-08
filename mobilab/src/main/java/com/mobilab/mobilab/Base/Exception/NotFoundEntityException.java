package com.mobilab.mobilab.Base.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends BaseRuntimeException {

    public NotFoundEntityException() {
        super(BaseExceptionMessages.Entity.NOT_FOUND);
    }
}

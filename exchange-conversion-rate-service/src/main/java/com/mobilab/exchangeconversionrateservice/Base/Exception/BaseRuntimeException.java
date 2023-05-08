package com.mobilab.exchangeconversionrateservice.Base.Exception;

public class BaseRuntimeException extends RuntimeException{

    private Integer code;

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}

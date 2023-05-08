package com.mobilab.mobilab.Transferation.Constants;

public interface TransferStatusMessages {

    Integer SUCCESSFUL_TRANSFER_CODE = 1;
    Integer UNSUCCESSFUL_TRANSFER_CODE = 0;

    String SUCCESSFUL_TRANSFER = "%f %s successfully transferred from (%s) to (%s).";
    String UNSUCCESSFUL_TRANSFER = "Unsuccessful transfer : %s.";

    String NOT_ENOUGH_BALANCE = "Balance is not enough";
    String ACCOUNT_NOT_FOUND = "Account not found";
    String EXCHANGE_SERVER_NOT_RESPOND = "Exchange server did not respond";
}

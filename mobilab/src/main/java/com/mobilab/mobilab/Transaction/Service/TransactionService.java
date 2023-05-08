package com.mobilab.mobilab.Transaction.Service;

import com.mobilab.mobilab.Account.Entity.Account;
import com.mobilab.mobilab.Base.Service.BaseService;
import com.mobilab.mobilab.Transaction.Entity.Transaction;
import com.mobilab.mobilab.Transaction.Enum.TransactionStatus;

public interface TransactionService extends BaseService<Transaction> {

    String submitTransaction(Account source, Account destination, double amount, TransactionStatus status, Double exchangeRate);

}

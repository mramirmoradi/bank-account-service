package com.mobilab.mobilab.Account.Service;

import com.mobilab.mobilab.Account.DTO.AccountChargeDTO;
import com.mobilab.mobilab.Account.DTO.AccountCreateDTO;
import com.mobilab.mobilab.Account.DTO.AccountResponseDTO;
import com.mobilab.mobilab.Account.DTO.AccountUpdateDTO;
import com.mobilab.mobilab.Account.Entity.Account;
import com.mobilab.mobilab.Base.Service.BaseService;

public interface AccountService extends BaseService<Account> {

    Account register(AccountCreateDTO createDTO);

    Account findByNumber(String number);

    void deleteByNumber(String number);

    Account increaseBalance(String number, double amount);

    Account decreaseBalance(String number, double amount);

    Account customUpdate(String number, AccountUpdateDTO updateDTO);

    String generateAccountNumber();

    Account charge(String number, AccountChargeDTO chargeDTO);


}

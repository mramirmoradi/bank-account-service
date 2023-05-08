package com.mobilab.mobilab.Account.Service;

import com.mobilab.mobilab.Account.DTO.AccountChargeDTO;
import com.mobilab.mobilab.Account.DTO.AccountCreateDTO;
import com.mobilab.mobilab.Account.DTO.AccountResponseDTO;
import com.mobilab.mobilab.Account.DTO.AccountUpdateDTO;
import com.mobilab.mobilab.Account.Entity.Account;
import com.mobilab.mobilab.Account.Exception.AccountCreationException;
import com.mobilab.mobilab.Account.Exception.NotCustomerAccountException;
import com.mobilab.mobilab.Account.Repository.AccountRepository;
import com.mobilab.mobilab.Base.Exception.NotFoundEntityException;
import com.mobilab.mobilab.Base.Service.BaseServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

/**
 * @author amirmoradi
 * @since 2022-12-03
 */
@Service
public class AccountServiceImp extends BaseServiceImp<Account> implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImp(AccountRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * This method is the only way for registering an account
     * Validation: isCustomerValidate, a customer can have only one account per currency otherwise AccountCreationException
     * @param createDTO as AccountCreateDTO.class
     * @return Account as Entity
     */
    @Transactional
    @Override
    public Account register(AccountCreateDTO createDTO) {
        if (isCustomerValidate(createDTO))
            throw new AccountCreationException();
        return this.create(new Account(
                createDTO.getName(),
                createDTO.getDescription(),
                generateAccountNumber(),
                createDTO.getCustomerId(),
                createDTO.getCurrency()));
    }

    /**
     * Finding account
     * @param number as unique Account number
     * @return Account.Class
     */
    @Override
    public Account findByNumber(String number) {
        Optional<Account> optionalAccount = repository.findByNumberAndDeleted(number, false);
        if (optionalAccount.isEmpty())
            throw new NotFoundEntityException();
        return optionalAccount.get();
    }

    /**
     * Deleting account, account record never going to delete from DB, just deleted field going to set as true
     * @param number as unique Account number
     */
    @Transactional
    @Override
    public void deleteByNumber(String number) {
        Account account = findByNumber(number);
        account.setDeleted(true);
        repository.save(account);
    }

    /**
     * Increasing Account Balance
     * @param number as Account number
     * @param amount as amount of money
     */
    @Transactional
    @Override
    public Account increaseBalance(String number, double amount) {
        Account account = findByNumber(number);
        account.setBalance(account.getBalance() + amount);
        return repository.save(account);
    }

    /**
     * Decreasing Account Balance
     * @param number as Account number
     * @param amount as amount of money
     */
    @Transactional
    @Override
    public Account decreaseBalance(String number, double amount) {
        Account account = findByNumber(number);
        account.setBalance(account.getBalance() - amount);
        return repository.save(account);
    }

    /**
     * Decreasing Account Balance
     * @param number as Account number
     * @param updateDTO as AccountUpdateDTO.class
     * @return Account as Account.Class
     */
    @Override
    public Account customUpdate(String number, AccountUpdateDTO updateDTO) {
        Account ref = findByNumber(number);
        ref.setName(updateDTO.getName());
        ref.setDescription(updateDTO.getDescription());
        return repository.save(ref);
    }

    /**
     * Account registration validation
     * @param createDTO as AccountCreateDTO.class
     * @return boolean as result, True : account by the currency exist, False : account by the currency doesn't exist
     */
    private boolean isCustomerValidate(AccountCreateDTO createDTO) {
        return repository.existsByCustomerIdAndCurrencyAndDeleted(createDTO.getCustomerId(), createDTO.getCurrency(), false);
    }

    /**
     * Generate Account Number, by 4x4 random digit number (ex: 5634 8656 9806 2545)
     * @return string as Account number
     */
    public String generateAccountNumber() {
        Random rand = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int n = rand.nextInt(9999 + 1 - 1000) + 1000;
            accountNumber.append(Integer.toString(n));
        }
        return accountNumber.toString();
    }

    @Override
    public Account charge(String number, AccountChargeDTO chargeDTO) {
        if (!repository.existsByNumberAndCustomerId(number, chargeDTO.getCustomerId()))
            throw new NotCustomerAccountException();
        return increaseBalance(number, chargeDTO.getAmount());
    }
}

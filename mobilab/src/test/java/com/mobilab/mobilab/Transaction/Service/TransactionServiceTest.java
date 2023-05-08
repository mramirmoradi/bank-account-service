package com.mobilab.mobilab.Transaction.Service;

import com.mobilab.mobilab.Account.Entity.Account;
import com.mobilab.mobilab.Account.Repository.AccountRepository;
import com.mobilab.mobilab.Account.Service.AccountService;
import com.mobilab.mobilab.Account.Service.AccountServiceImp;
import com.mobilab.mobilab.Base.Enum.Currency;
import com.mobilab.mobilab.Transaction.Entity.Transaction;
import com.mobilab.mobilab.Transaction.Enum.TransactionStatus;
import com.mobilab.mobilab.Transaction.Repository.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock private TransactionRepository repository;
    @Mock private AccountRepository accountRepository;
    private TransactionService service;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        service = new TransactionServiceImp(repository);
        accountService = new AccountServiceImp(accountRepository);
    }

    @Test
    void submitTransaction() {
        Account source =
                new Account("source", "source-description", accountService.generateAccountNumber(), 1L, Currency.USD);
        Account destination =
                new Account("destination", "destination-description", accountService.generateAccountNumber(), 2L, Currency.USD);

        service.submitTransaction(source, destination, 100.0, TransactionStatus.SUCCESS, 1.0);

        verify(repository, Mockito.times(2)).save(any());
    }
}
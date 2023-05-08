package com.mobilab.mobilab.Account.Service;

import com.mobilab.mobilab.Account.Constants.AccountExceptionMessages;
import com.mobilab.mobilab.Account.DTO.AccountChargeDTO;
import com.mobilab.mobilab.Account.DTO.AccountCreateDTO;
import com.mobilab.mobilab.Account.DTO.AccountUpdateDTO;
import com.mobilab.mobilab.Account.Entity.Account;
import com.mobilab.mobilab.Account.Exception.AccountCreationException;
import com.mobilab.mobilab.Account.Exception.NotCustomerAccountException;
import com.mobilab.mobilab.Account.Repository.AccountRepository;
import com.mobilab.mobilab.Base.Enum.Currency;
import com.mobilab.mobilab.Base.Exception.BaseExceptionMessages;
import com.mobilab.mobilab.Base.Exception.NotFoundEntityException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock private AccountRepository repository;
    private AccountService service;

    @BeforeEach
    void setUp() {
        service = new AccountServiceImp(repository);
    }

    @Test
    void register() {
        // given
        AccountCreateDTO createDTO = new AccountCreateDTO();
        createDTO.setName("name-1");
        createDTO.setDescription("description-1");
        createDTO.setCustomerId(1L);
        createDTO.setCurrency(Currency.USD);

        // when
        Account savedAccount = service.register(createDTO);
        Account account =
                new Account("name-1", "description-1", savedAccount.getNumber(), 1L, Currency.USD);

        // then
        ArgumentCaptor<Account> argumentCaptor =
                ArgumentCaptor.forClass(Account.class);

        verify(repository).save(argumentCaptor.capture());

        Account captorAccount = argumentCaptor.getValue();
        assertThat(captorAccount).isEqualTo(account);
        assertThat(captorAccount.getNumber().length()).isEqualTo(16);
        assertThat(captorAccount.getBalance()).isEqualTo(0.0);
    }

    @Test
    void registerValidation() {
        // given
        AccountCreateDTO createDTO = new AccountCreateDTO();
        createDTO.setName("name-1");
        createDTO.setDescription("description-1");
        createDTO.setCustomerId(1L);
        createDTO.setCurrency(Currency.USD);

        // when
        given(repository.
                existsByCustomerIdAndCurrencyAndDeleted(createDTO.getCustomerId(), createDTO.getCurrency(), false))
                .willReturn(true);

        // then
        assertThatThrownBy(() -> service.register(createDTO))
                .isInstanceOf(AccountCreationException.class)
                .hasMessageContaining(AccountExceptionMessages.ACCOUNT_CREATION_IS_NOT_POSSIBLE);

        verify(repository, never()).save(any());
    }

    @Test
    void findByNumberException() {
        // given
        // when
        given(repository.findByNumberAndDeleted("", false))
                .willReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> service.findByNumber(""))
                .isInstanceOf(NotFoundEntityException.class)
                .hasMessageContaining(BaseExceptionMessages.Entity.NOT_FOUND);
    }

    @Test
    void deleteByNumber() {
        // given
        String number = "5432758498345681";
        given(repository.findByNumberAndDeleted(number, false))
                .willReturn(Optional.of(new Account()));

        // when
        service.deleteByNumber(number);

        // then
        verify(repository).save(any());
    }

    @Test
    void increaseBalance() {
        // given
        String number = "5432758498345681";
        double amount = 100.0;
        given(repository.findByNumberAndDeleted(number, false))
                .willReturn(Optional.of(new Account()));

        // when
        service.increaseBalance(number, amount);

        // then
        verify(repository).save(any());
    }

    @Test
    void decreaseBalance() {
        // given
        String number = "5432758498345681";
        double amount = 100.0;
        given(repository.findByNumberAndDeleted(number, false))
                .willReturn(Optional.of(new Account()));

        // when
        service.decreaseBalance(number, amount);

        // then
        verify(repository).save(any());
    }

    @Test
    void customUpdate() {
        // given
        String number = "5432758498345681";
        AccountUpdateDTO updateDTO = new AccountUpdateDTO();
        given(repository.findByNumberAndDeleted(number, false))
                .willReturn(Optional.of(new Account()));

        // when
        service.customUpdate(number, updateDTO);

        // then
        verify(repository).save(any());
    }

    @Test
    void chargeAccount() {
        // given
        String number = "5432758498345681";
        AccountChargeDTO chargeDTO = new AccountChargeDTO();
        chargeDTO.setCustomerId(1L);
        chargeDTO.setAmount(10.0);

        // when
        given(repository.existsByNumberAndCustomerId(number,chargeDTO.getCustomerId())).willReturn(true);
        given(repository.findByNumberAndDeleted(number, false)).willReturn(Optional.of(new Account()));

        // then
        service.charge(number, chargeDTO);
        verify(repository).save(any());
    }

    @Test
    void chargeAccountException() {
        // given
        String number = "5432758498345681";
        AccountChargeDTO chargeDTO = new AccountChargeDTO();
        chargeDTO.setCustomerId(1L);
        chargeDTO.setAmount(10.0);

        // when
        given(repository.existsByNumberAndCustomerId(number,chargeDTO.getCustomerId())).willReturn(false);

        // then
        assertThatThrownBy(() -> service.charge(number, chargeDTO))
                .isInstanceOf(NotCustomerAccountException.class)
                .hasMessageContaining(AccountExceptionMessages.NOT_CUSTOMER_ACCOUNT);
    }
}
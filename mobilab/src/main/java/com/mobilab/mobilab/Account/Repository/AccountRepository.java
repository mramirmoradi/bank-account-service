package com.mobilab.mobilab.Account.Repository;

import com.mobilab.mobilab.Account.Entity.Account;
import com.mobilab.mobilab.Base.Enum.Currency;
import com.mobilab.mobilab.Base.Repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends BaseRepository<Account> {

    Optional<Account> findByNumberAndDeleted(String number, Boolean deleted);

    boolean existsByCustomerIdAndCurrencyAndDeleted(Long customerId, Currency currency, Boolean deleted);

    boolean existsByNumberAndCustomerId(String number, Long customerId);

}

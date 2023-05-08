package com.mobilab.mobilab.Account.Mapper;

import com.mobilab.mobilab.Account.DTO.AccountCreateDTO;
import com.mobilab.mobilab.Account.DTO.AccountResponseDTO;
import com.mobilab.mobilab.Account.DTO.AccountUpdateDTO;
import com.mobilab.mobilab.Account.Entity.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-06T20:51:36+0430",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4 (Amazon.com Inc.)"
)
@Component
public class AccountMapstructImpl implements AccountMapstruct {

    @Override
    public AccountResponseDTO accountToAccountResponseDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();

        accountResponseDTO.setId( account.getId() );
        accountResponseDTO.setName( account.getName() );
        accountResponseDTO.setNumber( account.getNumber() );
        accountResponseDTO.setDescription( account.getDescription() );
        accountResponseDTO.setCustomerId( account.getCustomerId() );
        accountResponseDTO.setBalance( account.getBalance() );
        accountResponseDTO.setCurrency( account.getCurrency() );
        accountResponseDTO.setCreatedDate( account.getCreatedDate() );

        return accountResponseDTO;
    }

    @Override
    public Account accountCreateDTOToAccount(AccountCreateDTO createDTO) {
        if ( createDTO == null ) {
            return null;
        }

        Account account = new Account();

        account.setName( createDTO.getName() );
        account.setDescription( createDTO.getDescription() );
        account.setCustomerId( createDTO.getCustomerId() );
        account.setCurrency( createDTO.getCurrency() );

        return account;
    }

    @Override
    public Account accountUpdateDTOToAccount(AccountUpdateDTO updateDTO) {
        if ( updateDTO == null ) {
            return null;
        }

        Account account = new Account();

        account.setName( updateDTO.getName() );
        account.setDescription( updateDTO.getDescription() );

        return account;
    }
}

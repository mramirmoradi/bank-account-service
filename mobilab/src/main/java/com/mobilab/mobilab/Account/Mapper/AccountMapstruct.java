package com.mobilab.mobilab.Account.Mapper;

import com.mobilab.mobilab.Account.DTO.AccountCreateDTO;
import com.mobilab.mobilab.Account.DTO.AccountResponseDTO;
import com.mobilab.mobilab.Account.DTO.AccountUpdateDTO;
import com.mobilab.mobilab.Account.Entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapstruct {

    AccountResponseDTO accountToAccountResponseDTO(Account account);
    Account accountCreateDTOToAccount(AccountCreateDTO createDTO);
    Account accountUpdateDTOToAccount(AccountUpdateDTO updateDTO);
}

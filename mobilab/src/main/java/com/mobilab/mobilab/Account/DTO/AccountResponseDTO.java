package com.mobilab.mobilab.Account.DTO;

import com.mobilab.mobilab.Base.Enum.Currency;
import lombok.Data;

import java.util.Date;

@Data
public class AccountResponseDTO {

    public Long id;
    public String name;
    public String number;
    public String description;
    public Long customerId;
    public Double balance;
    public Currency currency;
    public Date createdDate;

}

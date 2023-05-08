package com.mobilab.mobilab.Account.Entity;

import com.mobilab.mobilab.Base.Entity.BaseEntity;
import com.mobilab.mobilab.Base.Enum.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account represent as Bank Account
 * A customer can have two bank accounts, one on EUR currency another on USD
 * For any transfer request, account number will be use
 * Account number will be unique for every customer per currency
 * After registration, account can be modified by just name and description
 * There is no kind of limit on balance amount
 * @author amirmoradi
 * @since 2022-12-03
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @Column(unique = true, nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private String number;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private double balance;

    @Column(columnDefinition = "ENUM('USD','EUR')")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    // This constructor will be use for registering an account, Balance Default = 0.0
    public Account(String name, String description, String number, Long customerId, Currency currency) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.customerId = customerId;
        this.balance = 0.0;
        this.currency = currency;
    }

}

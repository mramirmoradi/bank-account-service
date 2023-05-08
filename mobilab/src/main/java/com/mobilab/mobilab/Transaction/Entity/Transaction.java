package com.mobilab.mobilab.Transaction.Entity;

import com.mobilab.mobilab.Base.Entity.BaseEntity;
import com.mobilab.mobilab.Base.Enum.Currency;
import com.mobilab.mobilab.Transaction.Enum.TransactionStatus;
import com.mobilab.mobilab.Transaction.Enum.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Transaction produce after a transfer happen between Accounts
 * On any transfer, two Transaction record will be produce, one RECEIVED, one SENT
 * There is two kind of transaction status, Success or Failure
 * All transaction have specific trackingCode,
 * On one transfer two Transaction get same trackingCode
 * @author amirmoradi
 * @since 2022-12-03
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity {

    private static final long TRACKING_CODE_BASE = 30000;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "source_account_number")
    private String sourceAccountNumber;

    @Column(name = "destination_account_number")
    private String destinationAccountNumber;

    @Column(name = "amount")
    private double amount;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(columnDefinition = "ENUM('USD','EUR')")
    @Enumerated(EnumType.STRING)
    private Currency sourceCurrency;

    @Column(columnDefinition = "ENUM('USD','EUR')")
    @Enumerated(EnumType.STRING)
    private Currency destinationCurrency;

    @Column(columnDefinition = "ENUM('SUCCESS','FAILURE')")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(columnDefinition = "ENUM('SENT','RECEIVED')")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "tracking_code")
    private String trackingCode;

}

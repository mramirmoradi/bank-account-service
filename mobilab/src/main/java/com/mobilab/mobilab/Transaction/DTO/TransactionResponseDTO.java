package com.mobilab.mobilab.Transaction.DTO;

import com.mobilab.mobilab.Base.Enum.Currency;
import com.mobilab.mobilab.Transaction.Enum.TransactionStatus;
import com.mobilab.mobilab.Transaction.Enum.TransactionType;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionResponseDTO {

    private Long id;

    private String accountNumber;

    private String sourceAccountNumber;

    private String destinationAccountNumber;

    private double amount;

    private double exchangeRate;

    private Currency sourceCurrency;

    private Currency destinationCurrency;

    private TransactionStatus status;

    private TransactionType type;

    private String trackingCode;

    private Date createdDate;
}

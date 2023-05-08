package com.mobilab.mobilab.Transaction.Specification;

import com.mobilab.mobilab.Base.Specification.BaseSpecification;
import com.mobilab.mobilab.Transaction.Entity.Transaction;
import com.mobilab.mobilab.Transaction.Enum.TransactionStatus;
import com.mobilab.mobilab.Transaction.Enum.TransactionType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Date;

public class TransactionSpecification extends BaseSpecification<Transaction> {

    private final String accountNumber;
    private final String sourceAccountNumber;
    private final String destinationAccountNumber;
    private final TransactionStatus status;
    private final String trackingCode;
    private final TransactionType type;

    public TransactionSpecification(Long id, Date startDate, Date endDate, String accountNumber, String sourceAccountNumber, String destinationAccountNumber, TransactionStatus status, String trackingCode, TransactionType type) {
        super(id, startDate, endDate);
        this.accountNumber = accountNumber;
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.status = status;
        this.trackingCode = trackingCode;
        this.type = type;
    }

    @Override
    public void prePredicate(Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (accountNumber != null)
            addPredicate(builder.equal(root.get("accountNumber"), accountNumber));
        if (sourceAccountNumber != null)
            addPredicate(builder.equal(root.get("sourceAccountNumber"), sourceAccountNumber));
        if (destinationAccountNumber != null)
            addPredicate(builder.equal(root.get("destinationAccountNumber"), destinationAccountNumber));
        if (status != null)
            addPredicate(builder.equal(root.get("status"), status));
        if (trackingCode != null)
            addPredicate(builder.equal(root.get("trackingCode"), trackingCode));
        if (type != null)
            addPredicate(builder.equal(root.get("type"), type));
    }
}

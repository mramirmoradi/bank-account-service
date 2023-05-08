package com.mobilab.mobilab.Account.Specification;

import com.mobilab.mobilab.Account.Entity.Account;
import com.mobilab.mobilab.Base.Enum.Currency;
import com.mobilab.mobilab.Base.Specification.BaseSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Date;

public class AccountSpecification extends BaseSpecification<Account> {

    private final String number;
    private final String name;
    private final Long customerId;
    private final Currency currency;
    private final Boolean deleted;

    public AccountSpecification(Long id, Date startDate, Date endDate, String number, String name, Long customerId, Currency currency, Boolean deleted) {
        super(id, startDate, endDate);
        this.number = number;
        this.name = name;
        this.customerId = customerId;
        this.currency = currency;
        this.deleted = deleted;
    }

    @Override
    public void prePredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (number != null)
            addPredicate(builder.equal(root.get("number"), number));
        if (name != null)
            addPredicate(builder.like(root.get("name"), "%" + name + "%"));
        if (customerId != null)
            addPredicate(builder.equal(root.get("customerId"), customerId));
        if (currency != null)
            addPredicate(builder.equal(root.get("currency"), currency));
        if (deleted != null)
            addPredicate(builder.equal(root.get("deleted"), deleted));
    }
}

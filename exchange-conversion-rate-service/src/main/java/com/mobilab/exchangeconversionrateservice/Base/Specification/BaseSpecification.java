package com.mobilab.exchangeconversionrateservice.Base.Specification;

import com.mobilab.exchangeconversionrateservice.Base.Entity.BaseEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BaseSpecification<T extends BaseEntity> implements Specification<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    protected Long id;
    protected Date startDate;
    protected Date endDate;

    private final List<Predicate> predicates;
    private Predicate predicateFinal;

    public BaseSpecification(Long id) {
        this.id = id;
        predicates = new ArrayList<>();
    }

    public BaseSpecification(Long id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        predicates = new ArrayList<>();
    }

    public void addPredicate(Predicate predicate) {
        this.predicates.add(predicate);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        prePredicate(root, query, builder);
        doPredicate(root, query, builder);
        postPredicate(root, query, builder);
        return predicateFinal;
    }

    public void doPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (id != null)
            this.addPredicate(builder.equal(root.get("id"), id));
        if (startDate != null && endDate != null)
            addPredicate(builder.between(root.get("createdDate"), startDate, endDate));

        query.distinct(true);
        List<Order> orders = new ArrayList<>();
        orders.add(builder.desc(root.get("createdDate")));
        query.orderBy(orders);
    }

    public void postPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (predicates.isEmpty())
            predicateFinal = builder.isNotNull(root.get("id"));
        else
            predicateFinal = predicates.get(0);
        for (Predicate pt : predicates) {
            predicateFinal = builder.and(predicateFinal, pt);
        }
    }

    public void prePredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    }
}

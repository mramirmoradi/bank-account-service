package com.mobilab.exchangeconversionrateservice.Base.Service;

import com.mobilab.exchangeconversionrateservice.Base.Entity.BaseEntity;
import com.mobilab.exchangeconversionrateservice.Base.Specification.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T create(T t);

    T update(long id, T t);

    void delete(long id);

    T find(long id);

    List<T> find();

    List<T> find(BaseSpecification<T> specification);

    Page<T> find(Pageable pageable);

    Page<T> find(BaseSpecification<T> specification, Pageable pageable);

    void preWrite(T t);

    void preCreate(T t);

    void preUpdate(long id, T t);

    void postWrite(T t);

    void postRead(T t);

    void preDelete(long id);

}

package com.mobilab.exchangeconversionrateservice.Base.Service;

import com.mobilab.exchangeconversionrateservice.Base.Entity.BaseEntity;
import com.mobilab.exchangeconversionrateservice.Base.Exception.NotFoundEntityException;
import com.mobilab.exchangeconversionrateservice.Base.Repository.BaseRepository;
import com.mobilab.exchangeconversionrateservice.Base.Specification.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImp<T extends BaseEntity> implements BaseService<T> {

    private final BaseRepository<T> repository;

    public BaseServiceImp(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T create(T t) {
        preCreate(t);
        return save(t);
    }

    @Override
    @Transactional
    public T update(long id, T t) {
        preUpdate(id, t);
        if (repository.findByIdAndDeleted(id, false).isEmpty())
            throw new NotFoundEntityException();
        save(t);
        return find(id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        preDelete(id);
        Optional<T> o = repository.findByIdAndDeleted(id, false);
        if (o.isEmpty())
            throw new NotFoundEntityException();
        T t = o.get();
        t.setDeleted(true);
        save(t);
    }

    @Override
    public Page<T> find(BaseSpecification<T> specification, Pageable pageable) {
        Page<T> page = repository.findAll(specification, pageable);
        for (T t : page)
            postRead(t);
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public T find(long id) {
        Optional<T> optional = repository.findByIdAndDeleted(id, false);
        if (optional.isEmpty())
            throw new NotFoundEntityException();
        T t = optional.get();
        postRead(t);
        return t;
    }

    private T save(T t) {
        preWrite(t);
        repository.save(t);
        postWrite(t);
        return t;
    }

    @Override
    public List<T> find(BaseSpecification<T> specification) {
        List<T> list = repository.findAll(specification);
        for (T t : list) {
            postRead(t);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> find() {
        List<T> ts = repository.findAll();
        for (T t : ts)
            postRead(t);
        return ts;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> find(Pageable pageable) {
        Page<T> page = repository.findAll(pageable);
        for (T t : page)
            postRead(t);
        return page;
    }

    @Override
    public void preWrite(T t) {
    }

    @Override
    public void preCreate(T t) {
    }

    @Override
    public void preUpdate(long id, T t) {

    }

    @Override
    public void postWrite(T t) {

    }

    @Override
    public void postRead(T t) {

    }

    @Override
    public void preDelete(long id) {

    }
}

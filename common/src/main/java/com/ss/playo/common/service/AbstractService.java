package com.ss.playo.common.service;

import com.ss.playo.common.interfaces.IDto;
import com.ss.playo.common.interfaces.IEntity;
import com.ss.playo.common.interfaces.dao.IJPADAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends IEntity, E extends Serializable> implements IService<T, E> {

    @Override
    public Optional<T> findById(E id) {
        return getDAO().findById(id);
    }

    @Override
    public List<T> findAll() {
        return getDAO().findAll();
    }

    @Override
    public T save(T entity) {
        return getDAO().save(entity);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        return getDAO().saveAll(entities);
    }

    protected abstract IJPADAO<T, E> getDAO();


}

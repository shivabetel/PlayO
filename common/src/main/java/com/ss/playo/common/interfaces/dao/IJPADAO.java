package com.ss.playo.common.interfaces.dao;


import com.ss.playo.common.interfaces.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJPADAO<T extends IEntity, E> extends JpaRepository<T, E>, CrudRepository<T, E> {
}

package com.ss.test.common.service;

import com.ss.playo.common.interfaces.IDto;
import com.ss.playo.common.interfaces.IEntity;
import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.common.service.IService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.Serializable;

import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("classpath:persistence-test.properties")
public abstract class AbstractServiceUnitTest<T extends IEntity, E extends Serializable> {


    void setUp() {


    }

    protected final  T stubDAOSave(T entity){
        when(getDAO().save(entity)).thenReturn(entity);
        return entity;
    }

    public abstract IService<T, E> getApi();

    protected abstract IJPADAO<T, E> getDAO();

    protected abstract T createNewEntity(IDto dto);
}

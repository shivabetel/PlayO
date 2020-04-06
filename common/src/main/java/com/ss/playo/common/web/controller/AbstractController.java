package com.ss.playo.common.web.controller;

import com.ss.playo.common.interfaces.IDto;
import com.ss.playo.common.interfaces.IEntity;
import com.ss.playo.common.interfaces.IEntityDTOMapper;
import com.ss.playo.common.service.IService;

import java.util.List;

public abstract class AbstractController<T extends IDto> {

    protected abstract IService getService();


    public void saveInternal(T dto) throws Exception{
       IEntity entity = this.getEntityDTOMapper().fromDTOToEntity(dto);
        getService().save(entity);
    }

    public List<T> findAllInternal() {
        return getService().findAll();
    }


    public abstract IEntityDTOMapper<? extends IEntity, T> getEntityDTOMapper();

}

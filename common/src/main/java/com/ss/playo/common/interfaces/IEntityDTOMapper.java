package com.ss.playo.common.interfaces;

public interface IEntityDTOMapper<T extends IEntity, E extends IDto> {

   E fromEntityToDTO(T entity);

   T fromDTOToEntity(E dto);
}

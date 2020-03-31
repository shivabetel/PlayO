package com.ss.playo.common.interfaces;

public interface IEntityDTOMapper<T extends IEntity, E extends IDto> {

   E fromEntity(T entity);

   T fromDTO(E dto);
}

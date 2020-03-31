package com.ss.playo.common.service;

import com.ss.playo.common.interfaces.IDto;
import com.ss.playo.common.interfaces.IEntity;
import com.ss.playo.common.interfaces.IOperations;

import java.io.Serializable;

public interface IService<T extends IEntity, E extends Serializable> extends IOperations<T, E> {

}

package com.ss.playo.common.interfaces;

import java.io.Serializable;

public interface IEntity<T extends Serializable> extends Serializable {

     T getId();
}

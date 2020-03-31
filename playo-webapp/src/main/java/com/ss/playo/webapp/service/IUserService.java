package com.ss.playo.webapp.service;

import com.ss.playo.common.service.IService;
import com.ss.playo.common.web.exception.UserAlreadyExistsException;
import com.ss.playo.webapp.dtos.UserDTO;
import com.ss.playo.webapp.persistence.dao.model.User;

public interface IUserService extends IService<User, String> {

    void register(UserDTO userDTO) throws UserAlreadyExistsException;
}

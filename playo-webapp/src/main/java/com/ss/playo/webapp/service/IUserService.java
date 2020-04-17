package com.ss.playo.webapp.service;

import com.ss.playo.common.service.IService;
import com.ss.playo.common.web.exception.UserAlreadyExistsException;
import com.ss.playo.common.web.exception.ValidationException;
import com.ss.playo.webapp.web.dtos.UserDTO;
import com.ss.playo.webapp.persistence.dao.model.User;

import java.util.Optional;

public interface IUserService extends IService<User, String> {

    User register(UserDTO userDTO) throws UserAlreadyExistsException;

    void verifyRegistration(String token) throws ValidationException;

    Boolean findByEmailId(String emailId);

}

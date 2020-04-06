package com.ss.playo.webapp.persistence.dao;

import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.webapp.persistence.dao.model.User;

import java.util.Optional;


public interface IUserJPADAO extends IJPADAO<User, String> {

    Optional<User> findByEmailId(String emailId);
}
